package com.peerIsland.order.mngt.service.app.service.impl;

import com.peerIsland.order.mngt.service.app.dao.OrderDao;
import com.peerIsland.order.mngt.service.app.datamapper.CreateOrderDataMapper;
import com.peerIsland.order.mngt.service.app.dto.CreateOrderRequest;
import com.peerIsland.order.mngt.service.app.dto.CreateOrderResponse;
import com.peerIsland.order.mngt.service.app.dto.ResultStatus;
import com.peerIsland.order.mngt.service.app.dto.Status;
import com.peerIsland.order.mngt.service.app.entity.CustomerOrder;
import com.peerIsland.order.mngt.service.app.service.CreateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {
    @Autowired
    private CreateOrderDataMapper createOrderDataMapper;
    @Autowired
    private OrderDao orderDao;

    @Override
    public CreateOrderResponse createOrder(String customerId,CreateOrderRequest createOrderRequest) {
        CustomerOrder order = createOrderDataMapper.toOrder(createOrderRequest);
        order.setCustomerId(customerId);
        CustomerOrder orderRes = orderDao.save(order);
        CreateOrderResponse response=new CreateOrderResponse();
        ResultStatus rs=new ResultStatus();
        rs.setStatus(Status.SUCCESS);
        rs.setMessage("Order has been successful created order id : "+orderRes.getOrderId());
        response.setStatus(rs);
        response.setOrderId(orderRes.getOrderId());
        return response;
    }
}
