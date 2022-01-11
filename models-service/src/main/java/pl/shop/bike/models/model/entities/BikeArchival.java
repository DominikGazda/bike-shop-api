package pl.shop.bike.models.model.entities;

import lombok.*;
import pl.shop.bike.models.model.entities.bikeParts.BrakeEntity;
import pl.shop.bike.models.model.entities.bikeParts.DriveEntity;
import pl.shop.bike.models.model.entities.bikeParts.FrameEntity;
import pl.shop.bike.models.model.enums.BikeType;
import pl.shop.bike.models.model.enums.GenderType;
import pl.shop.bike.models.model.enums.ItemType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bike_archival")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeArchival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Integer itemAmount;
    private String mark;
    private String color;

    @Enumerated(value = EnumType.STRING)
    private BikeType bikeType;
    private String bikeCode;

    @Enumerated(value = EnumType.STRING)
    private ItemType itemType = ItemType.BIKES;

    @Enumerated(value = EnumType.STRING)
    private GenderType genderType;

    @OneToOne(cascade = CascadeType.ALL)
    private BrakeEntity brake;

    @OneToOne(cascade = CascadeType.ALL)
    private DriveEntity drive;

    @OneToOne(cascade = CascadeType.ALL)
    private FrameEntity frame;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bike_id")
    private List<ImageEntity> images = new ArrayList<>();
}
