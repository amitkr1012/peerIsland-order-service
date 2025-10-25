package com.peerIsland.order.mngt.service.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetOrderResponse {
    private ResultStatus status;
    private List<OrderDTO> orderData;
}
