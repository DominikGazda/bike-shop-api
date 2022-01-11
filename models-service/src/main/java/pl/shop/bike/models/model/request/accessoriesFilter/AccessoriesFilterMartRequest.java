package pl.shop.bike.models.model.request.accessoriesFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessoriesFilterMartRequest {

    private Boolean kands;
    private Boolean merida;
    private Boolean trec;
    private Boolean cannondale;
    private Boolean kross;
    private Boolean eyen;
    private Boolean sks;
}
