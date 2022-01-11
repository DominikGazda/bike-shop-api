package pl.shop.bike.reactapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.shop.bike.reactapi.configuration.security.jwt.JwtConfig;
import pl.shop.bike.reactapi.configuration.security.jwt.JwtTokenVerifier;
import pl.shop.bike.reactapi.configuration.security.jwt.JwtUsernameAndPasswordAuthFilter;
import pl.shop.bike.reactapi.configuration.security.jwt.UserService;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserService userService, SecretKey secretKey, JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(getJwtUsernameAndPasswordAuthFilter())
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthFilter.class)
                .authorizeRequests()

                .antMatchers("/api/bikes").permitAll()
                .antMatchers("/api/bikes/new").permitAll()
                .antMatchers("/api/bikes/{name}").permitAll()
                .antMatchers("/api/bikes/{name}/sort").permitAll()
                .antMatchers("/api/bikes/sort/name").permitAll()

                .antMatchers("/api/bike/parts").permitAll()
                .antMatchers("/api/bike/parts/{name}").permitAll()
                .antMatchers("/api/bike/parts/sort").permitAll()
                .antMatchers("/api/bike/parts/sort/name").permitAll()

                .antMatchers("/api/accessories").permitAll()
                .antMatchers("/api/accessories/{name}").permitAll()
                .antMatchers("/api/accessories/sort/name").permitAll()

                .antMatchers("/api/workshop").permitAll()
                .antMatchers("/api/workshop/{name}").permitAll()
                .antMatchers("/api/workshop/sort/name").permitAll()
                .antMatchers("/api/users").permitAll()
                .antMatchers("/swagger-ui/*").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/swagger/**").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public JwtUsernameAndPasswordAuthFilter getJwtUsernameAndPasswordAuthFilter() throws Exception {
        final JwtUsernameAndPasswordAuthFilter filter = new JwtUsernameAndPasswordAuthFilter(authenticationManager(), jwtConfig, secretKey);
        filter.setFilterProcessesUrl("/api/react/user/login");
        return filter;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);

        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
