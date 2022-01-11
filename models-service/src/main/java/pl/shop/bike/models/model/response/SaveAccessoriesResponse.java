package pl.shop.bike.models.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.enums.AccessoriesType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveAccessoriesResponse {

    private String name;
    private Double price;
    private String mark;
    private AccessoriesType accessoriesType;
    private Integer itemAmount;
    private String color;
    private String description;
    private String productCode;
    private Integer quantity;
    private Double weight;
    private ErrorResponse errors;
}
