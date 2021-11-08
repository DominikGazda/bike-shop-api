package pl.shop.bike.models.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PumpOrderDto {

    private String name;
    private String mark;
    private String color;
    private Integer itemAmount;
    private Integer price;
    private Date orderDate;

}
