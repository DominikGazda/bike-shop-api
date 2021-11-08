package pl.shop.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.order.SaveOrderRequest;
import pl.shop.bike.models.model.response.SaveOrderResponse;
import pl.shop.bike.models.model.security.User;

@FeignClient(name = "update-service", url = "localhost:8093")
public interface UpdateServiceClient {

    @PostMapping("/api/update/order")
    public SaveOrderResponse saveOrder(@RequestBody SaveOrderRequest request);

    @PostMapping("/api/update/user")
    public User saveUser(@RequestBody UserEntity userEntity);
}
