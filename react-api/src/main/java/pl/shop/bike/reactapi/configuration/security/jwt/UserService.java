package pl.shop.bike.reactapi.configuration.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.shop.bike.models.model.security.User;
import pl.shop.commons.clients.ReadServiceClient;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private ReadServiceClient readServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = readServiceClient.getUserByUsername(username);
        } catch (Exception ex) {
            ex.printStackTrace();
            //todo obsluga wyjątków logi

            throw new UsernameNotFoundException("usre not found");
        }
        if (user == null) {
            throw new UsernameNotFoundException("usre not found");
        } else {
            return user;
        }
    }
}
