package pl.shop.bike.models.model.request.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikesFilterColorRequest {

    private Boolean black;
    private Boolean white;
    private Boolean blue;
    private Boolean red;
    private Boolean gray;
    private Boolean navy;
}
