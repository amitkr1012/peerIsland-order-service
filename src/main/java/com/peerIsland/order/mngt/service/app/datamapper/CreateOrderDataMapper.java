package com.peerIsland.order.mngt.service.app.datamapper;

import com.peerIsland.order.mngt.service.app.dto.CreateOrderRequest;
import com.peerIsland.order.mngt.service.app.dto.OrderItemDTO;
import com.peerIsland.order.mngt.service.app.entity.CustomerOrder;
import com.peerIsland.order.mngt.service.app.entity.OrderItem;
import com.peerIsland.order.mngt.service.app.entity.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreateOrderDataMapper {

    public CustomerOrder toOrder(CreateOrderRequest createOrderRequest) {
        CustomerOrder order = new CustomerOrder();
        order.setCreatedAt(Instant.now());
        order.setUpdatedAt(Instant.now());
        order.setOrderStatus(OrderStatus.PENDING);
        updateOrderItem(createOrderRequest.getItemList(), order);
        return order;
    }

    private void updateOrderItem(List<OrderItemDTO> itemList, CustomerOrder order) {
        if(itemList!=null && !itemList.isEmpty()){
            List<OrderItem> orderItems=new ArrayList<>();
            double totalPrice=0;
            for(OrderItemDTO ot:itemList){
                OrderItem orderItem=new OrderItem();
                orderItem.setSku(ot.getSku());
                orderItem.setPrice(ot.getPrice());
                orderItem.setProductId(ot.getProductId());
                orderItem.setQuantity(ot.getQuantity());
                totalPrice+=ot.getPrice()*ot.getQuantity();
                orderItem.setCustomerOrder(order);
                orderItems.add(orderItem);
            }
            order.setItemList(orderItems);
            order.setTotalPrice(totalPrice);
        }
    }
}
