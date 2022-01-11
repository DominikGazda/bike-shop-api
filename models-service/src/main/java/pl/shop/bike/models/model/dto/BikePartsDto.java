package pl.shop.bike.models.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikePartsDto {

    private BrakeDto brakeDto;
    private DriveDto driveDto;
    private FrameDto frameDto;
}
