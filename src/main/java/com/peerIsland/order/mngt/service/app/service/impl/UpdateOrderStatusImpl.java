package com.peerIsland.order.mngt.service.app.service.impl;

import com.peerIsland.order.mngt.service.app.dao.OrderDao;
import com.peerIsland.order.mngt.service.app.dto.ResultStatus;
import com.peerIsland.order.mngt.service.app.dto.Status;
import com.peerIsland.order.mngt.service.app.entity.OrderStatus;
import com.peerIsland.order.mngt.service.app.exception.InvalidStatusException;
import com.peerIsland.order.mngt.service.app.service.UpdateOrderStatus;
import com.peerIsland.order.mngt.service.app.util.OrderValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;

@Service
public class UpdateOrderStatusImpl implements UpdateOrderStatus {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderValidation orderValidation;

    @Override
    public ResultStatus updateOrderByStatus(String customerId, Long orderId, String toOrderStatus, String fromOrderStatus) {
        if(!orderValidation.isValidOrderStatusTransition(fromOrderStatus,toOrderStatus)){
            throw new InvalidStatusException("Order Status Transition is Invalid , please set Correct order Status transition");
        }
        int k = orderDao.updateOrderStatusByOrderIdAndCustomerId(OrderStatus.valueOf(toOrderStatus.toUpperCase()), orderId, OrderStatus.valueOf(fromOrderStatus.toUpperCase()),customerId, Instant.now());
        if(k==0){
            throw new NoSuchElementException("Order not found for this orderId :"+orderId);
        }
        ResultStatus rs=new ResultStatus();
        rs.setStatus(Status.SUCCESS);
        rs.setMessage("Updated order Status to "+toOrderStatus);
        return rs;
    }
}
