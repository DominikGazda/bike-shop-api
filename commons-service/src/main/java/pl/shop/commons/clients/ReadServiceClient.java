package pl.shop.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.entities.order.OrderEntity;
import pl.shop.bike.models.model.entities.service.ServiceEntity;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.accessoriesFilter.AccessoriesFilterRequest;
import pl.shop.bike.models.model.request.bikesFilter.BikesFilterRequest;
import pl.shop.bike.models.model.request.bikesPartsFilter.BikesPartsFilterRequest;
import pl.shop.bike.models.model.request.workshopFilter.WorkshopFilterRequest;
import pl.shop.bike.models.model.response.BikePartsNamesResponse;
import pl.shop.bike.models.model.response.OrderResponseDto;
import pl.shop.bike.models.model.security.User;

import java.util.List;
import java.util.Set;

@FeignClient(name = "read-service", url = "localhost:8089")
public interface ReadServiceClient {

    @GetMapping("/api/read/bikes")
    List<BikeEntity> getAllBikes();

    @GetMapping("/api/read/accessories")
    List<?> getAllAccessories(@RequestParam(name = "type", required = false) String type);

    @GetMapping("/api/read/workshop")
    List<?> getAllWorkshopItems(@RequestParam(name = "type", required = false) String type);

    @GetMapping("/api/read/bike/parts")
    List<?> getAllBikeParts(@RequestParam(name = "type", required = false) String type);

    @GetMapping("/api/read/bikes/{name}")
    BikeEntity findBikeByName(@PathVariable(name = "name") String name);

    @GetMapping("/api/read/accessories/{name}")
    ResponseEntity<?> findAccessoriesByName(@PathVariable(name = "name") String name);

    @GetMapping("/api/read/workshop/{name}")
    ResponseEntity<?> getWorkshopItemByName(@PathVariable(name = "name") String name);

    @GetMapping("/api/read/bike/parts/{name}")
    ResponseEntity<?> getBikePartByName(@PathVariable(name = "name") String name);

    @GetMapping("/api/read/bikes/new")
    List<BikeEntity> findNewestBikes();

    @PostMapping("/api/read/bikes/sort")
    List<BikeEntity> sortBikesByParameter(@RequestBody BikesFilterRequest bikesFilterRequest);

    @PostMapping("/api/read/bike/parts/sort")
    List<Set<?>> sortBikePartsByParameter(@RequestBody BikesPartsFilterRequest bikesFilterRequest);

    @PostMapping("/api/read/workshop/sort")
    List<Set<?>> sortWorkshopByParameter(@RequestBody WorkshopFilterRequest workshopFilterRequest);

    @PostMapping("/api/read/accessories/sort")
    List<Set<?>> sortAccessoriesByParameter(@RequestBody AccessoriesFilterRequest accessoriesFilterRequest);

    @GetMapping("/api/read/bikes/sort/name")
    List<BikeEntity> sortBikesByName(@RequestParam(name = "parameter") String parameter);

    @GetMapping("/api/read/bike/parts/sort/name")
    ResponseEntity<?> sortPartsByName(@RequestParam(name = "parameter") String parameter);

    @GetMapping("/api/read/orders")
    OrderResponseDto getOrdersForClient(@Nullable @RequestParam(name = "user", required = false) String user);

    @GetMapping("/api/read/users")
    UserEntity getUserInfo(@RequestParam(name = "username", required = false) String username,
                           @RequestParam(name = "userId", required = false) Long id);

    @GetMapping("api/read/user/{username}")
    User getUserByUsername(@PathVariable("username") String username);

    @PostMapping("/api/save")
    void saveUser(@RequestBody User user);

    @GetMapping("api/read/orders/{orderId}")
    ResponseEntity<OrderResponseDto> getOrderById(@PathVariable(name = "orderId") Long orderId);

    @GetMapping("/api/read/orders/details/{orderId}")
    ResponseEntity<OrderEntity> getOrderDetailsById(@PathVariable(name = "orderId") Long orderId);

    @GetMapping("api/read/bike/parts/names")
    BikePartsNamesResponse getBikePartsNames();

    @GetMapping("/api/read/accessories/sort/name")
    ResponseEntity<?> sortAccessoriesByName(@RequestParam(name = "parameter") String parameter);

    @GetMapping("/api/read/workshop/sort/name")
    ResponseEntity<?> sortWorkshopByName(@RequestParam(name = "parameter") String parameter);

    @GetMapping("/api/read/users/service")
    List<ServiceEntity> getServicesForUser(@RequestParam(name = "userId") Long userId);

    @GetMapping("/api/read/users/hours")
    List<String> getHoursFromDay(@RequestParam(name = "date") String date);
}
