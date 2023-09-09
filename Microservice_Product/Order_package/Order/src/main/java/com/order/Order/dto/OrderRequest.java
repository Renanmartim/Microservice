package com.order.Order.dto;


import com.order.Order.model.OrderLineItems;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    public List<OrderLineItemsDto> orderLineItemsListDto;
}
