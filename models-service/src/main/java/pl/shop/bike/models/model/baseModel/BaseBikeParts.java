package pl.shop.bike.models.model.baseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.enums.BikePartsType;
import pl.shop.bike.models.model.enums.ItemType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

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

    @Lob
    private String description;
    private String productCode;
    private Integer quantity;
    private Integer itemAmount;
    private Double weight;
    private boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private ItemType itemType = ItemType.PARTS;
}
