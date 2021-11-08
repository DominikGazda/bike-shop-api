package pl.shop.bike.models.model.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.enums.OrderStatus;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveOrderRequest {

    //    private User user  TODO: Użytkownik trzeba zrobic logike podaneDane / zalogowany juz ale to na froncie
    private String username;
    private OrderStatus status;
    private Date orderDate;
    private SaveOrderedItemsRequest saveOrderedItemsRequest;
}
