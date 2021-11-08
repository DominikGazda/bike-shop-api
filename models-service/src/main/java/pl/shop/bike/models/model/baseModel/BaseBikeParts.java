package pl.shop.bike.models.model.baseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.enums.AccessoriesType;
import pl.shop.bike.models.model.enums.BikePartsType;
import pl.shop.bike.models.model.enums.ItemType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseBikeParts {

    private String name;
    private Double price;
    private String mark;
    @Enumerated(EnumType.STRING)
    private BikePartsType bikePartsType;
    private String color;
    private String description;
    private String productCode;
    private Integer quantity;
    private Integer itemAmount;
    private Double weight;
    @Enumerated(EnumType.STRING)
    private ItemType itemType = ItemType.PARTS;

}
