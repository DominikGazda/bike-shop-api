package pl.shop.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.enums.AccessoriesType;
import pl.shop.bike.models.model.enums.BikePartsType;
import pl.shop.bike.models.model.enums.BikeType;
import pl.shop.bike.models.model.enums.WorkshopType;
import pl.shop.bike.models.model.request.*;
import pl.shop.bike.models.model.request.bike.SaveBikeResponse;
import pl.shop.bike.models.model.request.order.SaveOrderRequest;
import pl.shop.bike.models.model.response.*;
import pl.shop.bike.models.model.security.User;

import java.util.List;

@FeignClient(name = "update-service", url = "localhost:8093")
public interface UpdateServiceClient {

    @PostMapping("/api/update/order")
    SaveOrderResponse saveOrder(@RequestBody SaveOrderRequest request);

    @PostMapping("/api/update/user")
    User saveUser(@RequestBody UserEntity userEntity);

    @PostMapping("/api/update/order/status")
    ResponseEntity changeOrderStatus(@RequestParam(name = "parameter") String parameter,
                                     @RequestParam(name = "orderId") Integer orderId);

    @DeleteMapping("/api/update/order")
    ResponseEntity<DeleteOrderResponse> deleteOrder(@RequestParam(name = "orderId") Long orderId);

    @PostMapping("/api/update/bike")
    ResponseEntity<SaveBikeResponse> saveBike(@RequestBody SaveBikeRequest request);

    @PostMapping("/api/update/bike/part")
    ResponseEntity<SaveBikePartResponse> saveBikePart(@RequestBody SaveBikePartRequest request);

    @PostMapping("/api/update/accessories")
    ResponseEntity<SaveAccessoriesResponse> saveAccessories(@RequestBody SaveAccessoriesRequest request);

    @PostMapping("/api/update/workshop")
    ResponseEntity<SaveWorkshopResponse> saveWorkshop(@RequestBody SaveWorkshopRequest request);

    @DeleteMapping("/api/update/accessories")
    ResponseEntity<DeleteResponse> deleteAccessories(@RequestParam(name = "accessoriesId") Long accessoriesId,
                                                     @RequestParam(name = "type") AccessoriesType type);

    @DeleteMapping("/api/update/bike")
    ResponseEntity<DeleteResponse> deleteBikes(@RequestParam(name = "bikesId") Long bikesId,
                                               @RequestParam(name = "type") BikeType type);

    @DeleteMapping("/api/update/bike/part")
    ResponseEntity<DeleteResponse> deleteBikeParts(@RequestParam(name = "bikePartId") Long bikePartId,
                                                   @RequestParam(name = "type") BikePartsType type);

    @DeleteMapping("/api/update/workshop")
    ResponseEntity<DeleteResponse> deleteWorkshop(@RequestParam(name = "workshopId") Long workshopId,
                                                  @RequestParam(name = "type") WorkshopType type);

    @PostMapping("/api/update/user/save")
    UserEntity updateUser(@RequestParam(name = "pointer") String pointer, @RequestBody SaveUserRequest request);

    @PostMapping("/api/update/bike/save")
    ResponseEntity<SaveBikeResponse> updateBike(@RequestBody SaveBikeRequest request);

    @PostMapping("/api/update/bike/part/save")
    ResponseEntity<SaveBikePartResponse> updateBikePart(@RequestBody SaveBikePartRequest request);

    @PostMapping("/api/update/accessories/save")
    ResponseEntity<SaveAccessoriesResponse> updateAccessories(@RequestBody SaveAccessoriesRequest request);

    @PostMapping("/api/update/workshop/save")
    ResponseEntity<SaveWorkshopResponse> updateWorkshop(@RequestBody SaveWorkshopRequest request);

    @GetMapping("/api/update/user/sort")
    List<UserEntity> getUsersByUsername(@RequestParam(name = "parameter") String parameter);

    @PostMapping("/api/update/user/service")
    void saveService(@RequestBody SaveServiceRequest request);

    @DeleteMapping("/api/update/user/service")
    void deleteService(@RequestParam(name = "id") Long serviceId);

    @PostMapping("/api/update/user/modify")
    void modifyUser(@RequestParam(name = "id") Long userId,
                    @RequestParam(name = "action") String action);
}
