package pl.shop.bike.models.model.request.workshopFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shop.bike.models.model.request.accessoriesFilter.AccessoriesFilterMartRequest;
import pl.shop.bike.models.model.request.commons.BikesFilterPriceRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopFilterRequest {

    private WorkshopTypeFilterRequest type;
    private BikesFilterPriceRequest price;
    private AccessoriesFilterMartRequest mark;
}
