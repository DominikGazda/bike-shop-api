package pl.shop.commons.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.shop.bike.models.model.baseModel.Error;
import pl.shop.bike.models.model.response.ErrorResponse;
import pl.shop.commons.errors.exceptions.ItemNotFoundException;

import java.util.Date;

@RestController
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    protected final ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException exception, WebRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createError(exception));
    }

    private ErrorResponse createError(RuntimeException exception) {
        Error err = Error.builder()
                .message(exception.getMessage())
                .errorCode("msg.err.itemnotfound")
                .build();

        ErrorResponse response = new ErrorResponse();
        response.getErrors().add(err);
        response.setDate(new Date());
        return response;
    }
}
