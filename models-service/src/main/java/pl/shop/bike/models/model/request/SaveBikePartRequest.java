package pl.shop.bike.models.model.request;

import com.sun.istack.Nullable;
import lombok.*;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.enums.ItemType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveBikePartRequest {

    @Nullable
    private Long id;

    @NotEmpty(message = "msg.err.itemrequest.name.is.null")
    @Size(min = 2, message = "msg.err.itemrequest.description.is.null")
    private String name;

    @NotNull(message = "msg.err.itemrequest.price.is.null")
    private Double price;

    @NotEmpty(message = "msg.err.itemrequest.mark.is.null")
    private String mark;

    @NotEmpty(message = "msg.err.bikepartsrequest.bikepartstype.is.null")
    private String bikePartsType;

    @NotEmpty(message = "msg.err.itemrequest.color.is.null")
    private String color;

    @NotEmpty(message = "msg.err.itemrequest.description.is.null")
    private String description;

    @NotEmpty(message = "msg.err.itemrequest.productcode.is.null")
    private String productCode;

    @NotNull(message = "msg.err.itemrequest.quantity.is.null")
    private Integer quantity;

    private Integer itemAmount;

    @NotNull(message = "msg.err.itemrequest.weight.is.null")
    private Double weight;

    @NotNull(message = "msg.err.bikepartsrequest.frame.is.null")
    private Long frameSize;

    @NotEmpty(message = "msg.err.bikepartsrequest.braketype.is.null")
    private String brakeType;

    @NotEmpty(message = "msg.err.bikepartsrequest.group.is.null")
    private String group;

    @NotNull(message = "msg.err.bikepartsrequest.cable.is.null")
    private Long cableLength;

    @NotEmpty(message = "msg.err.bikepartsrequest.cassette.is.null")
    private String cassette;

    @NotEmpty(message = "msg.err.bikepartsrequest.gradation.is.null")
    private String gradation;

    @NotNull(message = "msg.err.bikepartsrequest.rows.is.null")
    private int rowsCount;

    private ItemType itemType = ItemType.PARTS;

    @NotEmpty(message = "msg.err.bikepartsrequest.images.is.null")
    private List<ImageEntity> imageEntityList = new ArrayList<>();
}
