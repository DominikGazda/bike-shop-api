package pl.shop.bike.models.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String name;
    private String surname;
    private String street;
    private Integer houseNumber;
    private Integer localNumber;
    private String zipCode;
    private String city;
    private String phone;
    private String email;
}
