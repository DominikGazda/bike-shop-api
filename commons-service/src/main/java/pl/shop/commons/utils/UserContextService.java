package pl.shop.commons.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserContextService {

    public String getUserFromContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String) {
            return (String) principal;
        } else {
            throw new IllegalArgumentException("Brak zdefiniowanego kontekstu użytkownika");
        }
    }
}
