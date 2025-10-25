package com.peerIsland.order.mngt.service.app.service;

import com.peerIsland.order.mngt.service.app.dto.CreateOrderRequest;
import com.peerIsland.order.mngt.service.app.dto.CreateOrderResponse;
import com.peerIsland.order.mngt.service.app.dto.OrderDTO;

public interface CreateOrderService {
    CreateOrderResponse createOrder(String customerId,CreateOrderRequest createOrderRequest);
}
