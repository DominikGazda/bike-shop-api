package pl.shop.bike.models.model.entities.accessories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.baseModel.BaseAccessories;
import pl.shop.bike.models.model.entities.ImageEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fenders")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FendersEntity extends BaseAccessories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String additionalItems;
    private String material;
    private String montage;
    private String wheelSize;
    private Integer fenderSize;
    private boolean fastMontage;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fenders_id")
    private List<ImageEntity> images = new ArrayList<>();
}
