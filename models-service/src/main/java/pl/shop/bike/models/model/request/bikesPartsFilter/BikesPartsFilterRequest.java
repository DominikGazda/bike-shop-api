package pl.shop.bike.models.model.request.bikesPartsFilter;

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
public class BikesPartsFilterRequest {

    private BikesPartsTypeRequest type;
    private BikesFilterColorRequest color;
    private BikesPartsMarkRequest mark;
    private BikesFilterPriceRequest price;
}
