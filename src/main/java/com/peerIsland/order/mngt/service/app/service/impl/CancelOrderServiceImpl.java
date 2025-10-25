package com.peerIsland.order.mngt.service.app.service.impl;

import com.peerIsland.order.mngt.service.app.dao.OrderDao;
import com.peerIsland.order.mngt.service.app.dto.ResultStatus;
import com.peerIsland.order.mngt.service.app.dto.Status;
import com.peerIsland.order.mngt.service.app.entity.CustomerOrder;
import com.peerIsland.order.mngt.service.app.entity.OrderStatus;
import com.peerIsland.order.mngt.service.app.exception.InvalidOrderOperationException;
import com.peerIsland.order.mngt.service.app.service.CancelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CancelOrderServiceImpl implements CancelOrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public ResultStatus cancelOrderId(long orderId, String customerId) {
        Optional<CustomerOrder> isOrder = orderDao.findOrderByOrderId(orderId);
        if(isOrder.isPresent()){
            CustomerOrder order = isOrder.get();
            if(!order.getOrderStatus().equals(OrderStatus.PENDING)){
                throw new InvalidOrderOperationException("Order can be canceled only when PENDING.");
            }else{
                int k = orderDao.updateOrderStatusByOrderIdAndCustomerId(OrderStatus.CANCELED, orderId,OrderStatus.PENDING,customerId, Instant.now());
                if(k==1){
                    ResultStatus rs=new ResultStatus();
                    rs.setStatus(Status.SUCCESS);
                    rs.setMessage("order has been canceled and order id : "+orderId);
                    return rs;
                }else{
                    throw new InvalidOrderOperationException("Failed to cancel order due to concurrent modification");
                }
            }
        }else{
            throw new NoSuchElementException(orderId+" OrderId not found");
        }
    }
}
