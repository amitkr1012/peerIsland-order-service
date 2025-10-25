package com.peerIsland.order.mngt.service.app.service;

import com.peerIsland.order.mngt.service.app.dto.ResultStatus;

public interface UpdateOrderStatus {

    ResultStatus updateOrderByStatus(String customerId,Long orderId, String toOrderStatus, String fromOrderStatus);
}
