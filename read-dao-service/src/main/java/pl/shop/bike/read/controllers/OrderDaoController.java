package pl.shop.bike.read.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.order.OrderEntity;
import pl.shop.bike.models.model.response.OrderResponseDto;
import pl.shop.bike.read.services.OrderDaoService;

@RestController
@RequestMapping("/api/read/orders")
public class OrderDaoController {

    @Autowired
    private OrderDaoService orderDaoService;

    @GetMapping
    public ResponseEntity<OrderResponseDto> getOrdersForClient(@RequestParam(name = "user", required = false) String user) {
        return ResponseEntity.status(HttpStatus.OK).body(orderDaoService.getOrders(user));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable(name = "orderId") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderDaoService.getOrderById(orderId));
    }

    @GetMapping("/details/{orderId}")
    public ResponseEntity<OrderEntity> getOrderDetailsById(@PathVariable(name = "orderId") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderDaoService.getOrderDetailsById(orderId));
    }
}
