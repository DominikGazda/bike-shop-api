package pl.shop.bike.models.model.request;

import com.sun.istack.Nullable;
import lombok.*;
import pl.shop.bike.models.model.enums.ItemType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveBikeRequest {

    @Nullable
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "msg.err.itemrequest.description.is.null")
    private String name;

    @NotEmpty
    @Size(min = 10, message = "msg.err.itemrequest.description.is.null")
    private String description;

    @NotNull(message = "msg.err.itemrequest.price.is.null")
    private Double price;

    @NotNull(message = "msg.err.itemrequest.quantity.is.null")
    private Integer quantity;

    @Nullable
    private Integer itemAmount;

    @NotEmpty(message = "msg.err.itemrequest.mark.is.null")
    private String mark;

    @NotEmpty(message = "msg.err.itemrequest.color.is.null")
    private String color;

    @NotEmpty(message = "msg.err.itemrequest.biketype.is.null")
    private String bikeType;

    @NotEmpty(message = "msg.err.itemrequest.bikecode.is.null")
    private String bikeCode;

    private ItemType itemType = ItemType.BIKES;

    @NotEmpty(message = "msg.err.itemrequest.gender.is.null")
    private String genderType;

    @NotEmpty(message = "msg.err.bikerequest.brake.is.null")
    private String brake;

    @NotEmpty(message = "msg.err.bikerequest.drive.is.null")
    private String drive;

    @NotEmpty(message = "msg.err.bikerequest.frame.is.null")
    private String frame;
}
