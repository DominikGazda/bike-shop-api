package pl.shop.bike.models.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.dto.order.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private List<BikeOrderDto> bike = new ArrayList<>();
    private List<BagOrderDto> bag = new ArrayList<>();
    private List<BottleOrderDto> bottle = new ArrayList<>();
    private List<FenderOrderDto> fender = new ArrayList<>();
    private List<PumpOrderDto> pump = new ArrayList<>();
    private List<BrakeOrderDto> brake = new ArrayList<>();
    private List<DriveOrderDto> drive = new ArrayList<>();
    private List<FrameOrderDto> frame = new ArrayList<>();
    private List<MaintenanceOrderDto> maintenance = new ArrayList<>();
    private List<RacksOrderDto> rack = new ArrayList<>();
    private List<ToolsOrderDto> tool = new ArrayList<>();
}
