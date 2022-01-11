package pl.shop.bike.models.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.enums.BikeType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String mark;
    private String color;
    private BikeType bikeType;
    private BikePartsDto bikeParts;
    private String bikeCode;
}
