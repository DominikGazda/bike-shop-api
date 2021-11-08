package pl.shop.bike.models.model.dto.order;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.baseModel.BaseOrder;
import pl.shop.bike.models.model.enums.OrderStatus;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BagOrderDto extends BaseOrder {

}
