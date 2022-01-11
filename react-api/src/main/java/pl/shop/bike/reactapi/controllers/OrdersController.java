package pl.shop.bike.reactapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.request.order.SaveOrderRequest;
import pl.shop.bike.models.model.response.OrderResponseDto;
import pl.shop.bike.models.model.response.SaveOrderResponse;
import pl.shop.commons.clients.ReadServiceClient;
import pl.shop.commons.clients.UpdateServiceClient;
import pl.shop.commons.utils.UserContextService;

@Slf4j
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
    public OrderResponseDto getOrdersForClient() {
        String user = userContextService.getUserFromContext();
        return readServiceClient.getOrdersForClient(user);
    }

    @PostMapping
    public SaveOrderResponse saveOrder(@RequestBody SaveOrderRequest request) {
        request.setUsername(userContextService.getUserFromContext());
        SaveOrderResponse response = updateServiceClient.saveOrder(request);
        return response;
    }

    @PostMapping("/status")
    public ResponseEntity changeOrderStatus(@RequestParam(name = "parameter") String parameter,
                                            @RequestParam(name = "orderId") Integer orderId) {
        log.info("Order id: {} parameter: {}", orderId, parameter);
        return updateServiceClient.changeOrderStatus(parameter, orderId);
    }


}
