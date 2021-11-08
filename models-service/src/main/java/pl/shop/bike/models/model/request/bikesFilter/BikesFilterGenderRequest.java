package pl.shop.bike.models.model.request.bikesFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikesFilterGenderRequest {

    private Boolean man;
    private Boolean woman;
}
