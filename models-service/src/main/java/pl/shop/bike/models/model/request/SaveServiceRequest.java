package pl.shop.bike.models.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveServiceRequest {

    private Long userId;
    private String date;
    private String time;
}
