package pl.shop.bike.models.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrameDto {
    private String frameSize;
    private String frameMaterial;
    private String fork;
    private Integer overstep;
}
