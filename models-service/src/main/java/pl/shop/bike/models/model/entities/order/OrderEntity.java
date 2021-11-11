package pl.shop.bike.models.model.entities.order;

import lombok.*;
import pl.shop.bike.models.model.baseModel.BaseBikeParts;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.entities.accessories.BagsEntity;
import pl.shop.bike.models.model.entities.accessories.BottlesEntity;
import pl.shop.bike.models.model.entities.accessories.FendersEntity;
import pl.shop.bike.models.model.entities.accessories.PumpEntity;
import pl.shop.bike.models.model.entities.address.AddressEntity;
import pl.shop.bike.models.model.entities.bikeParts.BrakeEntity;
import pl.shop.bike.models.model.entities.bikeParts.DriveEntity;
import pl.shop.bike.models.model.entities.bikeParts.FrameEntity;
import pl.shop.bike.models.model.entities.user.UserEntity;
import pl.shop.bike.models.model.entities.workshop.MaintenanceEntity;
import pl.shop.bike.models.model.entities.workshop.RacksEntity;
import pl.shop.bike.models.model.entities.workshop.ToolsEntity;
import pl.shop.bike.models.model.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    private Date orderDate;

    private Integer amount;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "bike_id")
    private BikeEntity bike;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "bags_id")
    private BagsEntity bag;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "bottles_id")
    private BottlesEntity bottle;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "fenders_id")
    private FendersEntity fender;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "pump_id")
    private PumpEntity pump;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "brake_id")
    private BrakeEntity brake;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "drive_id")
    private DriveEntity drive;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "frame_id")
    private FrameEntity frame;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "maintenance_id")
    private MaintenanceEntity maintenance;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "rack_id")
    private RacksEntity rack;

    //    @ManyToOne(cascade =CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "tool_id")
    private ToolsEntity tool;

    //    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

}
