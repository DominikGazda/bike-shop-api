package pl.shop.bike.models.model.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.entities.address.AddressEntity;
import pl.shop.bike.models.model.enums.OrderStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveOrderRequest {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    private OrderStatus status;

    @NotNull
    private Date orderDate;

    private SaveOrderedItemsRequest saveOrderedItemsRequest;
    private AddressEntity address;
}
