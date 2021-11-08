package pl.shop.bike.models.model.request.bikesFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikesFilterFrameRequest {

    private Boolean frame26;
    private Boolean frame27;
    private Boolean frame28;
    private Boolean frame29;
}
