package pl.shop.bike.models.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriveDto {
    private String drive;
    private String frontDerailleur;
    private String rearDerailleur;
    private String shifters;
    private String crankMechanism;
    private String freewheel;
}
