package pl.shop.bike.models.model.request.bike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.enums.BikeType;
import pl.shop.bike.models.model.enums.GenderType;
import pl.shop.bike.models.model.response.ErrorResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveBikeResponse {

    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Integer itemAmount;
    private String mark;
    private String color;
    private BikeType bikeType;
    private String bikeCode;
    private GenderType genderType;
    private String brake;
    private String drive;
    private String frame;
    private ErrorResponse errors;
}
