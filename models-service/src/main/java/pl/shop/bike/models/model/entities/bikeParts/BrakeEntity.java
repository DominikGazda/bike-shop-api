package pl.shop.bike.models.model.entities.bikeParts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.shop.bike.models.model.baseModel.BaseBikeParts;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.entities.ImageEntity;
import pl.shop.bike.models.model.enums.BrakeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brake")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BrakeEntity extends BaseBikeParts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cableLength;

    @Enumerated(EnumType.STRING)
    private BrakeType brakeType;

    @OneToOne(cascade = CascadeType.ALL)
    private BikeEntity bike;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "brake_id")
    private List<ImageEntity> images = new ArrayList<>();
}
