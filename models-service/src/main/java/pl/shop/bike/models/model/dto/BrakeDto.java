package pl.shop.bike.models.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.enums.BrakeType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrakeDto {

    private BrakeType brakeType;
    private String name;
}
