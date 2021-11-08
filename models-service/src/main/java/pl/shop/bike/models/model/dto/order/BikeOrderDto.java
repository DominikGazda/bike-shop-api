package pl.shop.bike.models.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.enums.BikeType;
import pl.shop.bike.models.model.enums.OrderStatus;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeOrderDto {

    private String name;
    private String mark;
    private String color;
    private BikeType bikeType;
    private Integer itemAmount;
    private Double price;
    private Date orderDate;
    private OrderStatus status;
}
