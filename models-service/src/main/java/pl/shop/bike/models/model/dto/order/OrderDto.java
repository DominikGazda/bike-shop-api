package pl.shop.bike.models.model.dto.order;

import lombok.*;
import pl.shop.bike.models.model.enums.OrderStatus;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private OrderStatus status = OrderStatus.NEW;
    private Date orderDate;
    private Integer amount;

    private BikeOrderDto bike;
    private BagOrderDto bag;
    private BottleOrderDto bottle;
    private FenderOrderDto fender;
    private PumpOrderDto pump;
    private BrakeOrderDto brake;
    private DriveOrderDto drive;
    private FrameOrderDto frame;
    private MaintenanceOrderDto maintenance;
    private RacksOrderDto rack;
    private ToolsOrderDto tool;
}
