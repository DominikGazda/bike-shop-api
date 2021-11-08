package pl.shop.bike.models.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.enums.OrderStatus;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagOrderDto {

    private String name;
    private String mark;
    private String color;
    private Integer itemAmount;
    private Integer price;
    private Date orderDate;
    private OrderStatus status;
}
