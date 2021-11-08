package pl.shop.bike.models.model.baseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.enums.AccessoriesType;
import pl.shop.bike.models.model.enums.ItemType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Data
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseAccessories {

    private String name;
    private Double price;
    private String mark;
    @Enumerated(EnumType.STRING)
    private AccessoriesType accessoriesType;
    private Integer itemAmount;
    private String color;
    private String description;
    private String productCode;
    private Integer quantity;
    private Double weight;
    @Enumerated(EnumType.STRING)
    private ItemType itemType = ItemType.ACCESSORIES;

}
