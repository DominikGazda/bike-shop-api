package pl.shop.commons.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;
import pl.shop.bike.models.model.baseModel.Error;
import pl.shop.bike.models.model.enums.ItemType;
import pl.shop.bike.models.model.request.bike.SaveBikeResponse;
import pl.shop.bike.models.model.response.ErrorResponse;
import pl.shop.bike.models.model.response.SaveAccessoriesResponse;
import pl.shop.bike.models.model.response.SaveBikePartResponse;
import pl.shop.bike.models.model.response.SaveWorkshopResponse;

import java.util.*;

public class ErrorsUtil {

    public static ResponseEntity<?> createErrorResponse(BindingResult result, ItemType itemType) {
        final Locale locale = new Locale("pl", "PL");
        final ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        List<ObjectError> errorList = result.getAllErrors();
        ErrorResponse response = new ErrorResponse();
        System.out.println(Arrays.toString(result.getAllErrors().toArray()));

        errorList.forEach(err -> {
            Error error = Error.builder()
                    .errorCode(err.getDefaultMessage())
                    .message(bundle.getString(err.getDefaultMessage()))
                    .build();
            response.getErrors().add(error);
        });
        response.setDate(new Date());

        return createResponse(response, itemType);
    }

    private static ResponseEntity<?> createResponse(ErrorResponse response, ItemType itemType) {

        switch (itemType) {
            case BIKES:
                SaveBikeResponse saveBikeResponse = SaveBikeResponse.builder()
                        .errors(response)
                        .build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveBikeResponse);
            case ACCESSORIES:
                SaveAccessoriesResponse saveAccessoriesResponse = SaveAccessoriesResponse.builder()
                        .errors(response)
                        .build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveAccessoriesResponse);
            case PARTS:
                SaveBikePartResponse saveBikePartResponse = SaveBikePartResponse.builder()
                        .errors(response)
                        .build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveBikePartResponse);
            case WORKSHOP:
                SaveWorkshopResponse saveWorkshopResponse = SaveWorkshopResponse.builder()
                        .errors(response)
                        .build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveWorkshopResponse);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Błąd systemu");
    }
}
