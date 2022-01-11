package pl.shop.bike.models.model.request;

import lombok.*;
import pl.shop.bike.models.model.dto.AddressDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequest {

    @NotEmpty(message = "msg.err.userrequest.username.is.null")
    private String username;

    @NotEmpty(message = "msg.err.userrequest.password.is.null")
    private String password;

    @NotEmpty(message = "msg.err.userrequest.oldpassword.is.null")
    private String oldPassword;

    @NotEmpty(message = "msg.err.userrequest.name.is.null")
    private String name;

    @NotEmpty(message = "msg.err.userrequest.surname.is.null")
    private String surname;

    @NotNull(message = "msg.err.userrequest.address.is.null")
    private AddressDto address;

}
