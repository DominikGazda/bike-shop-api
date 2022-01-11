package pl.shop.bike.models.model.request.bikesPartsFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikesPartsMarkRequest {

    private Boolean kands;
    private Boolean merida;
    private Boolean trec;
    private Boolean shimano;
    private Boolean sram;
}
