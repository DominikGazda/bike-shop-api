package pl.shop.bike.reactapi.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = {
        "pl.shop.bike.reactapi",
        "pl.shop.commons"
})
@EnableFeignClients(basePackages = "pl.shop.commons.clients")
@EntityScan(basePackages = {
        "pl.shop.bike.models.model.security",
})


public class ReactApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactApiApplication.class, args);
    }


}
