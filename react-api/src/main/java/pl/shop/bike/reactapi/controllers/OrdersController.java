package pl.shop.bike.reactapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.request.order.SaveOrderRequest;
import pl.shop.bike.models.model.response.OrderEntityResponse;
import pl.shop.bike.models.model.response.SaveOrderResponse;
import pl.shop.bike.models.model.response.OrderEntityDtoResponse;
import pl.shop.bike.reactapi.services.UserContextService;
import pl.shop.commons.clients.ReadServiceClient;
import pl.shop.commons.clients.UpdateServiceClient;


@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private UserContextService userContextService;

    @Autowired
    private UpdateServiceClient updateServiceClient;

    @Autowired
    private ReadServiceClient readServiceClient;

    @GetMapping("/user")
    public OrderEntityDtoResponse getOrdersForClient(){
        String user = userContextService.getUserFromContext();
        return readServiceClient.getOrdersForClient(user);
    }

    @PostMapping
    public SaveOrderResponse saveOrder(@RequestBody SaveOrderRequest request){
        request.setUsername(userContextService.getUserFromContext());
        SaveOrderResponse response = updateServiceClient.saveOrder(request);
        return response;
    }
}
