package pl.shop.bike.models.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.baseModel.BaseOrder;
import pl.shop.bike.models.model.enums.BikeType;
import pl.shop.bike.models.model.enums.OrderStatus;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BikeOrderDto extends BaseOrder {

    private Date orderDate;
    private OrderStatus status;
    private BikeType bikeType;
}
