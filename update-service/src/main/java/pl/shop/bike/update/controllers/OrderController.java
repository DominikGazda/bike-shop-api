package pl.shop.bike.update.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.entities.accessories.BagsEntity;
import pl.shop.bike.models.model.entities.accessories.BottlesEntity;
import pl.shop.bike.models.model.entities.accessories.FendersEntity;
import pl.shop.bike.models.model.entities.accessories.PumpEntity;
import pl.shop.bike.models.model.entities.address.AddressEntity;
import pl.shop.bike.models.model.entities.bikeParts.BrakeEntity;
import pl.shop.bike.models.model.entities.bikeParts.DriveEntity;
import pl.shop.bike.models.model.entities.bikeParts.FrameEntity;
import pl.shop.bike.models.model.entities.order.OrderEntity;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.entities.workshop.MaintenanceEntity;
import pl.shop.bike.models.model.entities.workshop.RacksEntity;
import pl.shop.bike.models.model.entities.workshop.ToolsEntity;
import pl.shop.bike.models.model.enums.OrderStatus;
import pl.shop.bike.models.model.request.order.SaveOrderRequest;
import pl.shop.bike.models.model.request.order.SaveOrderedItemsRequest;
import pl.shop.bike.models.model.response.DeleteOrderResponse;
import pl.shop.bike.models.model.response.SaveOrderResponse;
import pl.shop.commons.dao.addressDao.AddressRepository;
import pl.shop.commons.dao.orderDAO.OrderRepository;
import pl.shop.commons.dao.userDAO.UserEntityRepository;
import pl.shop.commons.errors.exceptions.OrderNotFoundException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/update/order")
public class OrderController {

    private UserEntityRepository userEntityRepository;
    private OrderRepository orderRepository;
    private EntityManager entityManager;
    private AddressRepository addressRepository;

    public OrderController(UserEntityRepository userEntityRepository, OrderRepository orderRepository, EntityManager entityManager, AddressRepository addressRepository) {
        this.userEntityRepository = userEntityRepository;
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
        this.addressRepository = addressRepository;
    }

    @DeleteMapping()
    ResponseEntity<DeleteOrderResponse> deleteOrder(@RequestParam(name = "orderId") Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Nie znaleziono zamówienia o podanym id"));

        orderRepository.delete(orderEntity);

        DeleteOrderResponse response = DeleteOrderResponse.builder()
                .id(orderEntity.getId())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/status")
    ResponseEntity changeOrderStatus(@RequestParam(name = "parameter") String parameter,
                                     @RequestParam(name = "orderId") Integer orderId) {
        OrderEntity orderEntity = orderRepository.findById(Long.valueOf(orderId))
                .orElseThrow(IllegalArgumentException::new);

        switch (parameter) {
            case "Nowe":
                orderEntity.setStatus(OrderStatus.NEW);
                orderRepository.save(orderEntity);
                break;
            case "Realizowane":
                orderEntity.setStatus(OrderStatus.IN_PROGRESS);
                orderRepository.save(orderEntity);
                break;
            case "Zrealizowane":
                orderEntity.setStatus(OrderStatus.COMPLETE);
                orderRepository.save(orderEntity);
                break;
            default:
                throw new IllegalArgumentException("Podany parametr jest nieprawidłowy");
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Transactional
    public SaveOrderResponse saveOrder(@RequestBody SaveOrderRequest request) {
        log.info("Save user process...");
        UserEntity user = userEntityRepository.findByUsername(request.getUsername());

        if (request.getAddress().getZipCode() == null) {
            Optional<AddressEntity> address = addressRepository.findById(user.getAddress().getId());
            request.setAddress(address.get());
        }

        log.info("Save Order fields");
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setStatus(OrderStatus.NEW);
        orderEntity.setOrderDate(request.getOrderDate());
        orderEntity.setUser(user);

        log.info("Save order list -> iterate");
        SaveOrderedItemsRequest items = request.getSaveOrderedItemsRequest();

        for (BagsEntity bag : items.getBags()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(bag);
            tmpEntity.setAmount(bag.getItemAmount());
            tmpEntity.setBag(bag);
            entityManager.persist(tmpEntity);
        }
        for (BottlesEntity bottle : items.getBottles()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(bottle);
            tmpEntity.setAmount(bottle.getItemAmount());
            tmpEntity.setBottle(bottle);
            entityManager.persist(tmpEntity);
        }
        for (FendersEntity fender : items.getFenders()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(fender);
            tmpEntity.setAmount(fender.getItemAmount());
            tmpEntity.setFender(fender);
            entityManager.persist(tmpEntity);
        }
        for (PumpEntity pump : items.getPumps()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(pump);
            tmpEntity.setAmount(pump.getItemAmount());
            tmpEntity.setPump(pump);
            entityManager.persist(tmpEntity);
        }
        for (MaintenanceEntity maintenance : items.getMaintenances()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(maintenance);
            tmpEntity.setAmount(maintenance.getItemAmount());
            tmpEntity.setMaintenance(maintenance);
            entityManager.persist(tmpEntity);
        }
        for (RacksEntity rack : items.getRacks()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(rack);
            tmpEntity.setAmount(rack.getItemAmount());
            tmpEntity.setRack(rack);
            entityManager.persist(tmpEntity);
        }
        for (ToolsEntity tool : items.getTools()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(tool);
            tmpEntity.setAmount(tool.getItemAmount());
            tmpEntity.setTool(tool);
            entityManager.persist(tmpEntity);
        }
        for (BrakeEntity brake : items.getBrakes()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(brake);
            tmpEntity.setAmount(brake.getItemAmount());
            tmpEntity.setBrake(brake);
            entityManager.persist(tmpEntity);
        }
        for (DriveEntity drive : items.getDrives()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(drive);
            tmpEntity.setAmount(drive.getItemAmount());
            tmpEntity.setDrive(drive);
            entityManager.persist(tmpEntity);
        }
        for (FrameEntity frame : items.getFrames()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(frame);
            tmpEntity.setAmount(frame.getItemAmount());
            tmpEntity.setFrame(frame);
            entityManager.persist(tmpEntity);
        }
        for (BikeEntity bike : items.getBikes()) {
            OrderEntity tmpEntity = buildOrderEntity(orderEntity);
            tmpEntity.setAddress(request.getAddress());

            entityManager.merge(bike);
            tmpEntity.setAmount(bike.getItemAmount());
            tmpEntity.setBike(bike);
            entityManager.persist(tmpEntity);
        }

        return SaveOrderResponse.builder()
                .orderStatus(orderEntity.getStatus())
                .orderDate(orderEntity.getOrderDate())
                .build();
    }

    private OrderEntity buildOrderEntity(OrderEntity entity) {
        return OrderEntity.builder()
                .status(OrderStatus.NEW)
                .orderDate(entity.getOrderDate())
                .user(entity.getUser())
                .build();

    }
}
