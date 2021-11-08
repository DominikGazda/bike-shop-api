package pl.shop.bike.models.model.baseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.enums.OrderStatus;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseOrder {

    private String name;
    private String mark;
    private String color;
    private Integer itemAmount;
    private Double price;
    private Date orderDate;
    private OrderStatus status;
}
