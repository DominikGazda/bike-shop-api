package pl.shop.bike.models.model.request.accessoriesFilter;

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
public class AccessoriesFilterRequest {

    private AccessoriesFilterTypeRequest type;
    private BikesFilterColorRequest color;
    private BikesFilterPriceRequest price;
    private AccessoriesFilterMartRequest mark;

}
