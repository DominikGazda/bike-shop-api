package pl.shop.bike.update.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaveInvoker {

    private final List<SaveUser> SaveUserList;

    public SaveInvoker(List<SaveUser> SaveUserList) {
        this.SaveUserList = SaveUserList;
    }

    public SaveUser lookUp(String pointer) throws IllegalArgumentException {
        Optional<SaveUser> service = SaveUserList
                .stream()
                .filter(s -> s.canHandle(pointer))
                .findFirst();

        if (!service.isPresent()) {
            throw new IllegalArgumentException("Podano błędny parametr zapytania");
        }

        return service.get();
    }
}
