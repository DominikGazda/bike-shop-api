package pl.shop.bike.models.model.request.accessoriesFilter;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessoriesFilterTypeRequest {

    private boolean bags;
    private boolean bottles;
    private boolean fenders;
    private boolean pumps;
}
