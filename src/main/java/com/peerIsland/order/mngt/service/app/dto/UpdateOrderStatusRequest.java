package com.peerIsland.order.mngt.service.app.dto;

import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    private long orderId;
    private String toStatus;
    private String fromStatus;
}
