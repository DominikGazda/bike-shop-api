package pl.shop.bike.models.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikePartsNamesResponse {

    private List<String> brakeNames;
    private List<String> driveNames;
    private List<String> frameNames;
}
