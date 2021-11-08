package pl.shop.bike.models.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.baseModel.BaseOrder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FrameOrderDto extends BaseOrder {

}
