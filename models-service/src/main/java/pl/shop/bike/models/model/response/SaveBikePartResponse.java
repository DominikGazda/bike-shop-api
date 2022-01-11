package pl.shop.bike.models.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.enums.BikePartsType;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveBikePartResponse {

    private String name;
    private Double price;
    private String mark;
    private BikePartsType bikePartsType;
    private String color;
    private String description;
    private String productCode;
    private Integer quantity;
    private Integer itemAmount;
    private Double weight;
    private Long frameSize;
    private ErrorResponse errors;
}
