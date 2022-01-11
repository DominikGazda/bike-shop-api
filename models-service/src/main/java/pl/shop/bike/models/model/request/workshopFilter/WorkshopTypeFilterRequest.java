package pl.shop.bike.models.model.request.workshopFilter;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkshopTypeFilterRequest {

    private boolean maintenance;
    private boolean racks;
    private boolean tools;
}
