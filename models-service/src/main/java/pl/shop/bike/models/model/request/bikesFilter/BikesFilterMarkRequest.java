package pl.shop.bike.models.model.request.bikesFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikesFilterMarkRequest {

    private Boolean kands;
    private Boolean merida;
    private Boolean cross;
    private Boolean bmx;
}
