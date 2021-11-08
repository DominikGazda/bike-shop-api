package pl.shop.bike.models.model.request.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikesFilterPriceRequest {

    private Double lowPrice;
    private Double highPrice;
}
