package com.peerIsland.order.mngt.service.app.dto;

import com.peerIsland.order.mngt.service.app.entity.OrderItem;
import com.peerIsland.order.mngt.service.app.entity.OrderStatus;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class OrderDTO {
    private List<OrderItemDTO> itemList;
    private double totalPrice;
    private OrderStatus orderStatus;
    private String customerId;
    private Instant createdAt;
    private Instant updatedAt;
    private long version;
}
