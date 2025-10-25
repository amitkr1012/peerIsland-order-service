package com.peerIsland.order.mngt.service.app.service.impl;

import com.peerIsland.order.mngt.service.app.dao.OrderDao;
import com.peerIsland.order.mngt.service.app.datamapper.GetOrderDataMapper;
import com.peerIsland.order.mngt.service.app.dto.GetOrderResponse;
import com.peerIsland.order.mngt.service.app.entity.CustomerOrder;
import com.peerIsland.order.mngt.service.app.entity.OrderStatus;
import com.peerIsland.order.mngt.service.app.exception.InvalidStatusException;
import com.peerIsland.order.mngt.service.app.service.GetOrderDetailsService;
import com.peerIsland.order.mngt.service.app.util.OrderValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetOrderDetailsServiceImpl implements GetOrderDetailsService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private GetOrderDataMapper getOrderDataMapper;
    @Autowired
    private OrderValidation orderValidation;
    @Override
    public GetOrderResponse getAllOrder(String customerId) {
        List<CustomerOrder> orders = orderDao.findAllOrderByCustomerId(customerId);
        return getOrderDataMapper.toGetOrderResponse(orders);
    }

    @Override
    public GetOrderResponse getOrderDetailByOrderId(String customerId, long orderId) {
        CustomerOrder order = orderDao.findOrderByOrderId(orderId).orElseThrow(()->new NoSuchElementException("Order Id is not found "+orderId));
        List<CustomerOrder> orders=new ArrayList<>();
        orders.add(order);
        return getOrderDataMapper.toGetOrderResponse(orders);
    }

    @Override
    public GetOrderResponse getAllOrderByStatus(String customerId, String status) {
        if(!orderValidation.isValidStatus(status)){
            throw new InvalidStatusException(status);
        }
        List<CustomerOrder> orders = orderDao.findAllOrderByCustomerIdAndOrderStatus(customerId, OrderStatus.valueOf(status.toUpperCase()));
        return getOrderDataMapper.toGetOrderResponse(orders);
    }
}
