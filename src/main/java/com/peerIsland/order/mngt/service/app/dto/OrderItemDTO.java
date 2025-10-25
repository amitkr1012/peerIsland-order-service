package com.peerIsland.order.mngt.service.app.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private  String sku;
    private long productId;
    private int quantity;
    private double price;
}
