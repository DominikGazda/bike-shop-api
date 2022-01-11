package pl.shop.bike.models.model.request;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class SaveAccessoriesRequest {

    @Nullable
    private Long id;

    @NotEmpty(message = "msg.err.itemrequest.name.is.null")
    @Size(min = 2, message = "msg.err.itemrequest.description.is.null")
    private String name;

    @NotNull(message = "msg.err.itemrequest.price.is.null")
    private Double price;

    @NotEmpty(message = "msg.err.itemrequest.mark.is.null")
    private String mark;

    @NotEmpty(message = "msg.err.accessoriesrequest.acccessoriestype.is.null")
    private String accessoriesType;

    private Integer itemAmount;

    @NotEmpty(message = "msg.err.itemrequest.color.is.null")
    private String color;

    @NotEmpty(message = "msg.err.itemrequest.description.is.null")
    private String description;

    @NotEmpty(message = "msg.err.itemrequest.productcode.is.null")
    private String productCode;

    @NotNull(message = "msg.err.itemrequest.quantity.is.null")
    private Integer quantity;

    @NotNull(message = "msg.err.itemrequest.weight.is.null")
    private Double weight;

    private ItemType itemType = ItemType.ACCESSORIES;

    @NotNull(message = "msg.err.bikepartsrequest.capacity.is.null")
    private Integer capacity;

    @NotEmpty(message = "msg.err.accessoriesrequest.installation.is.null")
    private String installation;

    private boolean fastMontage;
    private boolean waterproof;

    @NotEmpty(message = "msg.err.accessoriesrequest.dimensions.is.null")
    private String dimensions;

    private String material;
    private boolean thermal;
    private String additionalItems;
    private String montage;
    private String wheelSize;
    private Integer fenderSize;
    private Double maxPressure;
    private boolean manometer;
    private boolean isCatridge;
    private String pumpType;
    private String valveType;

    @NotEmpty(message = "msg.err.bikepartsrequest.images.is.null")
    private List<ImageEntity> imageEntityList = new ArrayList<>();
}
