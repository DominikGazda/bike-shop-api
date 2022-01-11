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
public class SaveWorkshopRequest {

    @Nullable
    private Long id;

    @NotEmpty(message = "msg.err.itemrequest.name.is.null")
    @Size(min = 2, message = "msg.err.itemrequest.description.is.null")
    private String name;

    @NotNull(message = "msg.err.itemrequest.price.is.null")
    private Double price;

    @NotEmpty(message = "msg.err.itemrequest.mark.is.null")
    private String mark;

    @NotEmpty(message = "msg.err.itemrequest.description.is.null")
    private String description;

    @NotEmpty(message = "msg.err.itemrequest.productcode.is.null")
    private String productCode;

    @NotEmpty(message = "msg.err.bikepartsrequest.usages.is.null")
    private String usages;

    @NotNull(message = "msg.err.itemrequest.quantity.is.null")
    private Integer quantity;

    private Integer itemAmount;

    @NotNull(message = "msg.err.bikepartsrequest.capacity.is.null")
    private Double capacity;

    @NotEmpty(message = "msg.err.bikepartsrequest.workshoptype.is.null")
    private String workshopType;

    @NotEmpty(message = "msg.err.itemrequest.color.is.null")
    private String color;

    private ItemType itemType = ItemType.WORKSHOP;

    @NotEmpty(message = "msg.err.bikepartsrequest.images.is.null")
    private List<ImageEntity> imageEntities = new ArrayList<>();

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"name\":" + "\"" + name + "\"," +
                "\"price\":" + price + "," +
                "\"mark\":" + "\"" + mark + "\"," +
                "\"description\":" + "\"" + description + "\"," +
                "\"productCode\":" + "\"" + productCode + "\"," +
                "\"usages\":" + "\"" + usages + "\"," +
                "\"quantity\":" + quantity + "," +
                "\"itemAmount\":" + itemAmount + "," +
                "\"capacity\":" + capacity + "," +
                "\"workshopType\":" + "\"" + workshopType + "\"," +
                "\"color\":" + "\"" + color + "\"," +
                "\"itemType\":" + "\"" + itemType + "\"" +
                '}';
    }
}
