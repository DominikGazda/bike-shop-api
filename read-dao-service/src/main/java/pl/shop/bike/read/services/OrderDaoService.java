package pl.shop.bike.read.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.shop.bike.models.model.dto.order.*;
import pl.shop.bike.models.model.entities.order.OrderEntity;
import pl.shop.bike.models.model.response.OrderEntityDtoResponse;
import pl.shop.commons.dao.orderDAO.OrderRepository;

import java.util.List;

@Service
public class OrderDaoService {

    @Autowired
    private OrderRepository orderRepository;

    //TODO: Dużo mapowania tutaj ez
    public OrderEntityDtoResponse getOrders(String user) {
        List<OrderEntity> orderEntities = orderRepository.findAllByUserUsername(user);
        OrderEntityDtoResponse response = new OrderEntityDtoResponse();

        orderEntities.forEach((order) -> {
            if (order.getBike() != null) {
                BikeOrderDto bike = BikeOrderDto.builder()
                        .bikeType(order.getBike().getBikeType())
                        .name(order.getBike().getName())
                        .mark(order.getBike().getMark())
                        .price(order.getBike().getPrice())
                        .color(order.getBike().getColor())
                        .itemAmount(order.getBike().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getBike().add(bike);
            }
            if (order.getBag() != null) {
                BagOrderDto bag = BagOrderDto.builder()
                        .name(order.getBag().getName())
                        .mark(order.getBag().getMark())
                        .price(order.getBag().getPrice())
                        .color(order.getBag().getColor())
                        .itemAmount(order.getBag().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getBag().add(bag);
            }
            if (order.getBottle() != null) {
                BottleOrderDto bottle = BottleOrderDto.builder()
                        .name(order.getBottle().getName())
                        .mark(order.getBottle().getMark())
                        .price(order.getBottle().getPrice())
                        .color(order.getBottle().getColor())
                        .itemAmount(order.getBottle().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getBottle().add(bottle);
            }
            if (order.getFender() != null) {
                FenderOrderDto fender = FenderOrderDto.builder()
                        .name(order.getFender().getName())
                        .mark(order.getFender().getMark())
                        .price(order.getFender().getPrice())
                        .color(order.getFender().getColor())
                        .itemAmount(order.getFender().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getFender().add(fender);
            }
            if (order.getPump() != null) {
                PumpOrderDto pump = PumpOrderDto.builder()
                        .name(order.getPump().getName())
                        .mark(order.getPump().getMark())
                        .price(order.getPump().getPrice())
                        .color(order.getPump().getColor())
                        .itemAmount(order.getPump().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getPump().add(pump);
            }
            if (order.getBrake() != null) {
                BrakeOrderDto brake = BrakeOrderDto.builder()
                        .name(order.getBrake().getName())
                        .mark(order.getBrake().getMark())
                        .price(order.getBrake().getPrice())
                        .color(order.getBrake().getColor())
                        .itemAmount(order.getBrake().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getBrake().add(brake);
            }
            if (order.getDrive() != null) {
                DriveOrderDto drive = DriveOrderDto.builder()
                        .name(order.getDrive().getName())
                        .mark(order.getDrive().getMark())
                        .price(order.getDrive().getPrice())
                        .color(order.getDrive().getColor())
                        .itemAmount(order.getDrive().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getDrive().add(drive);
            }
            if (order.getFrame() != null) {
                FrameOrderDto frame = FrameOrderDto.builder()
                        .name(order.getFrame().getName())
                        .mark(order.getFrame().getMark())
                        .price(order.getFrame().getPrice())
                        .color(order.getFrame().getColor())
                        .itemAmount(order.getFrame().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getFrame().add(frame);
            }
            if (order.getMaintenance() != null) {
                MaintenanceOrderDto maintenance = MaintenanceOrderDto.builder()
                        .name(order.getMaintenance().getName())
                        .mark(order.getMaintenance().getMark())
                        .price(order.getMaintenance().getPrice())
                        .itemAmount(order.getMaintenance().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getMaintenance().add(maintenance);
            }
            if (order.getRack() != null) {
                RacksOrderDto racks = RacksOrderDto.builder()
                        .name(order.getRack().getName())
                        .mark(order.getRack().getMark())
                        .price(order.getRack().getPrice())
                        .itemAmount(order.getRack().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getRack().add(racks);
            }
            if (order.getTool() != null) {
                ToolsOrderDto tools = ToolsOrderDto.builder()
                        .name(order.getTool().getName())
                        .mark(order.getTool().getMark())
                        .price(order.getTool().getPrice())
                        .itemAmount(order.getTool().getItemAmount())
                        .orderDate(order.getOrderDate())
                        .build();
                response.getTool().add(tools);
            }

        });

        return response;
    }
}

