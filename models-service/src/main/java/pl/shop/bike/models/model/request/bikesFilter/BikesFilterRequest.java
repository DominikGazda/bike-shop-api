package pl.shop.bike.models.model.request.bikesFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.request.commons.BikesFilterColorRequest;
import pl.shop.bike.models.model.request.commons.BikesFilterPriceRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikesFilterRequest {

    private BikesFilterMarkRequest mark;
    private BikesFilterColorRequest color;
    private BikesFilterFrameRequest frame;
    private BikesFilterGenderRequest gender;
    private BikesFilterPriceRequest price;
}
