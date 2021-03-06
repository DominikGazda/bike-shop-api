package pl.shop.bike.models.model.baseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.enums.ItemType;
import pl.shop.bike.models.model.enums.WorkshopType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseWorkshop {

    private String name;
    private Double price;
    private String mark;
    private String description;
    private String productCode;
    private String usages;
    private boolean isDeleted;
    private Integer quantity;
    private Integer itemAmount;
    private Double capacity;

    @Enumerated(EnumType.STRING)
    private WorkshopType workshopType;

    @Enumerated(EnumType.STRING)
    private ItemType itemType = ItemType.WORKSHOP;
}
