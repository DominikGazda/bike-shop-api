package pl.shop.bike.models.model.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.entities.BikeEntity;
import pl.shop.bike.models.model.entities.accessories.BagsEntity;
import pl.shop.bike.models.model.entities.accessories.BottlesEntity;
import pl.shop.bike.models.model.entities.accessories.FendersEntity;
import pl.shop.bike.models.model.entities.accessories.PumpEntity;
import pl.shop.bike.models.model.entities.bikeParts.BrakeEntity;
import pl.shop.bike.models.model.entities.bikeParts.DriveEntity;
import pl.shop.bike.models.model.entities.bikeParts.FrameEntity;
import pl.shop.bike.models.model.entities.workshop.MaintenanceEntity;
import pl.shop.bike.models.model.entities.workshop.RacksEntity;
import pl.shop.bike.models.model.entities.workshop.ToolsEntity;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveOrderedItemsRequest {
    private List<BikeEntity> bikes;
    private List<BagsEntity> bags;
    private List<BottlesEntity> bottles;
    private List<FendersEntity> fenders;
    private List<PumpEntity> pumps;
    private List<BrakeEntity> brakes;
    private List<DriveEntity> drives;
    private List<FrameEntity> frames;
    private List<MaintenanceEntity> maintenances;
    private List<RacksEntity> racks;
    private List<ToolsEntity> tools;

}
