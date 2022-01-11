package pl.shop.bike.models.model.request.bike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import pl.shop.bike.models.model.enums.BikeType;
import pl.shop.bike.models.model.enums.GenderType;
import pl.shop.bike.models.model.enums.ItemType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveBikeRequest {

    @NotNull(message = "msg.err.savebikerequest.name.is.null")
    @NotEmpty(message = "msg.err.savebikerequest.name.is.null")
    private String name;

    @NotNull(message = "msg.err.savebikerequest.description.is.null")
    @NotEmpty(message = "msg.err.savebikerequest.description.is.null")
    private String description;

    @NotNull(message = "msg.err.itemrequest.price.is.null")
    private Double price;

    @NotNull(message = "msg.err.itemrequest.quantity.is.null")
    private Integer quantity;

    @Nullable
    private Integer itemAmount;

    @NotNull(message = "msg.err.itemrequest.mark.is.null")
    @NotEmpty(message = "msg.err.itemrequest.mark.is.null")
    private String mark;

    @NotNull(message = "msg.err.itemrequest.color.is.null")
    @NotEmpty(message = "msg.err.itemrequest.color.is.null")
    private String color;

    @NotNull(message = "msg.err.itemrequest.biketype.is.null")
    private BikeType bikeType;

    @NotNull(message = "msg.err.itemrequest.bikecode.is.null")
    @NotEmpty(message = "msg.err.itemrequest.bikecode.is.null")
    private String bikeCode;

    @NotNull
    private ItemType itemType = ItemType.BIKES;

    @NotNull(message = "msg.err.itemrequest.gender.is.null")
    private GenderType genderType;

    @NotNull(message = "msg.err.bikerequest.brake.is.null")
    @NotEmpty(message = "msg.err.bikerequest.brake.is.null")
    private String brake;

    @NotNull(message = "msg.err.bikerequest.drive.is.null")
    @NotEmpty(message = "msg.err.bikerequest.drive.is.null")
    private String drive;

    @NotNull(message = "msg.err.bikerequest.frame.is.null")
    @NotEmpty(message = "msg.err.bikerequest.frame.is.null")
    private String frame;
}
