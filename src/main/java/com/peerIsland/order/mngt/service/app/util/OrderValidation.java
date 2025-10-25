package com.peerIsland.order.mngt.service.app.util;

import com.peerIsland.order.mngt.service.app.entity.OrderStatus;
import com.peerIsland.order.mngt.service.app.exception.InvalidStatusException;
import org.springframework.stereotype.Component;

@Component
public class OrderValidation {

    public boolean isValidOrderStatusTransition(String fromOrderStatus,String toOrderStatus){
        if(!isValidStatus(fromOrderStatus) || !isValidStatus(toOrderStatus)){
            throw new InvalidStatusException("Order Status is Invalid, Please set valid Status");
        }else if(OrderStatus.CANCELED.name().equalsIgnoreCase(fromOrderStatus) || OrderStatus.DELIVERED.name().equalsIgnoreCase(fromOrderStatus)){
            return false;
        }else{
            return switch (OrderStatus.valueOf(fromOrderStatus.toUpperCase())) {
                case PENDING ->
                        OrderStatus.valueOf(toOrderStatus.toUpperCase()) == OrderStatus.PROCESSING || OrderStatus.valueOf(toOrderStatus.toUpperCase()) == OrderStatus.CANCELED;
                case PROCESSING -> OrderStatus.valueOf(toOrderStatus.toUpperCase()) == OrderStatus.SHIPPED;
                case SHIPPED -> OrderStatus.valueOf(toOrderStatus.toUpperCase()) == OrderStatus.DELIVERED;
                default -> false;
            };
        }
    }

    public boolean isValidStatus(String status) {
        for(OrderStatus os:OrderStatus.values()){
            if(os.name().equalsIgnoreCase(status)){
                return true;
            }
        }
        return false;
    }
}
