package com.peerIsland.order.mngt.service.app.dto;

import lombok.Data;

@Data
public class CreateOrderResponse {
    private ResultStatus status;
    private long orderId;
}
