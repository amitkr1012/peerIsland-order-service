package com.peerIsland.order.mngt.service.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private List<OrderItemDTO> itemList;
}
