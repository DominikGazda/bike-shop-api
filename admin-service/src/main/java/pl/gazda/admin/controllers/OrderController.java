package pl.gazda.admin.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.order.OrderEntity;
import pl.shop.bike.models.model.response.DeleteOrderResponse;
import pl.shop.bike.models.model.response.OrderResponseDto;
import pl.shop.commons.clients.ReadServiceClient;
import pl.shop.commons.clients.UpdateServiceClient;

@Slf4j
@RestController
@RequestMapping("/api/adm/orders")
public class OrderController {

    @Autowired
    private ReadServiceClient readServiceClient;

    @Autowired
    UpdateServiceClient updateServiceClient;

    @GetMapping
    public OrderResponseDto getOrders(){
       return readServiceClient.getOrdersForClient(null);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable(name = "orderId") Long orderId){
        return readServiceClient.getOrderById(orderId);
    }

    @GetMapping("/details/{orderId}")
    public ResponseEntity<OrderEntity> getOrderDetailsById(@PathVariable(name = "orderId") Long orderId){
        return readServiceClient.getOrderDetailsById(orderId);
    }

    @DeleteMapping
    public ResponseEntity<DeleteOrderResponse> deleteOrder(@RequestParam(name = "orderId") Long orderId){
        log.info("Order id: {}", orderId);
        return updateServiceClient.deleteOrder(orderId);
    }
}
