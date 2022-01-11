package pl.shop.bike.models.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.enums.WorkshopType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveWorkshopResponse {

    private String name;
    private Double price;
    private String mark;
    private String description;
    private String productCode;
    private String usages;
    private Integer quantity;
    private Integer itemAmount;
    private Double capacity;
    private WorkshopType workshopType;
    private ErrorResponse errors;
}
