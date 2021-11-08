package pl.shop.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.request.bikesFilter.BikesFilterRequest;
import pl.shop.bike.models.model.response.OrderEntityDtoResponse;
import pl.shop.bike.models.model.response.OrderEntityResponse;
import pl.shop.bike.models.model.security.User;

import java.util.List;

@FeignClient(name = "read-service", url = "localhost:8089")
public interface ReadServiceClient {

    @GetMapping("/api/read/bikes")
    public List<BikeEntity> getAllBikes();

    @GetMapping("/api/read/accessories")
    public List<?> getAllAccessories(@RequestParam(name = "type", required = false) String type);

    @GetMapping("/api/read/workshop")
    public List<?> getAllWorkshopItems(@RequestParam(name = "type", required = false) String type);

    @GetMapping("/api/read/bike/parts")
    public List<?> getAllBikeParts(@RequestParam(name = "type", required = false) String type);

    @GetMapping("/api/read/bikes/{name}")
    public BikeEntity findBikeByName(@PathVariable(name = "name") String name);

    @GetMapping("/api/read/accessories/{name}")
    public ResponseEntity<?> findAccessoriesByName(@PathVariable(name = "name") String name);

    @GetMapping("/api/read/workshop/{name}")
    public ResponseEntity<?> getWorkshopItemByName(@PathVariable(name = "name") String name);

    @GetMapping("/api/read/bike/parts/{name}")
    public ResponseEntity<?> getBikePartByName(@PathVariable(name = "name") String name);

    @GetMapping("/api/read/bikes/new")
    public List<BikeEntity> findNewestBikes();

    @PostMapping("/api/read/bikes/sort")
    public List<BikeEntity> sortBikesByParameter(@RequestBody BikesFilterRequest bikesFilterRequest);

    @GetMapping("/api/read/bikes/sort/name")
    public List<BikeEntity> sortBikesByName(@RequestParam(name="parameter") String parameter);

    @GetMapping("/api/read/bike/parts/sort/name")
    public ResponseEntity<?> sortPartsByName(@RequestParam(name = "parameter") String parameter);

    @GetMapping("/api/read/orders")
    public OrderEntityDtoResponse getOrdersForClient(@RequestParam(name = "user") String user);

    @GetMapping("/api/read/users")
    public UserEntity getUserInfo(@RequestParam(name = "username") String username);

    @GetMapping("api/read/user/{username}")
    User getUserByUsername(@PathVariable("username") String username);

    @PostMapping("/api/save")
    void saveUser(@RequestBody User user);
}
