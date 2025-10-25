package com.peerIsland.order.mngt.service.app.service;

import com.peerIsland.order.mngt.service.app.dto.GetOrderResponse;

public interface GetOrderDetailsService {

    GetOrderResponse getAllOrder(String customerId);

    GetOrderResponse getOrderDetailByOrderId(String customerId,long orderId);

    GetOrderResponse getAllOrderByStatus(String customerId, String status);
}
