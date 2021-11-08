package pl.shop.bike.read.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.shop.bike.models.model.entities.order.OrderEntity;
import pl.shop.bike.models.model.response.OrderEntityDtoResponse;
import pl.shop.bike.models.model.response.OrderEntityResponse;
import pl.shop.bike.read.services.OrderDaoService;
import pl.shop.commons.dao.orderDAO.OrderRepository;

import java.util.List;

@RestController
@RequestMapping("/api/read/orders")
public class OrderDaoController {

   @Autowired
   private OrderDaoService orderDaoService;

    @GetMapping
    public OrderEntityDtoResponse getOrdersForClient(@RequestParam (name = "user") String user) {
        return orderDaoService.getOrders(user);
    }

}
