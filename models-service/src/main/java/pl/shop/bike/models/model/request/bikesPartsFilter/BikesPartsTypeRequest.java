package pl.shop.bike.models.model.request.bikesPartsFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikesPartsTypeRequest {

    private Boolean brake;
    private Boolean frame;
    private Boolean drive;
    private Boolean test1;
}
