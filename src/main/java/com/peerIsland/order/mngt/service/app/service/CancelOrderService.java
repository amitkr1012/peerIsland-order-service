package com.peerIsland.order.mngt.service.app.service;

import com.peerIsland.order.mngt.service.app.dto.ResultStatus;

public interface CancelOrderService {

    ResultStatus cancelOrderId(long orderId,String customerId);
}
