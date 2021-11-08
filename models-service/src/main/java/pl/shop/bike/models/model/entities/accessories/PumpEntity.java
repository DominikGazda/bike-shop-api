package pl.shop.bike.models.model.entities.accessories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.baseModel.BaseAccessories;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.enums.PumpType;
import pl.shop.bike.models.model.enums.ValveType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pump")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PumpEntity extends BaseAccessories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double maxPressure;
    private boolean manometer;
    private boolean isCatridge;
    private PumpType pumpType;
    private ValveType valveType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pump_id")
    private List<ImageEntity> images = new ArrayList<>();
}
