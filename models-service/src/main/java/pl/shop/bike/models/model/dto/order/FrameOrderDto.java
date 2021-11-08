package pl.shop.bike.models.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrameOrderDto {

    private String name;
    private Integer price;
    private String mark;
    private String color;
    private Integer itemAmount;
    private Date orderDate;
}
