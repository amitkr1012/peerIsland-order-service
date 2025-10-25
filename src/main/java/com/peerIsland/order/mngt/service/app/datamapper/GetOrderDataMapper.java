package com.peerIsland.order.mngt.service.app.datamapper;

import com.peerIsland.order.mngt.service.app.dto.*;
import com.peerIsland.order.mngt.service.app.entity.CustomerOrder;
import com.peerIsland.order.mngt.service.app.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetOrderDataMapper {

    public GetOrderResponse toGetOrderResponse(List<CustomerOrder> orders) {
        GetOrderResponse res=new GetOrderResponse();
        if(orders!=null && !orders.isEmpty()){
            List<OrderDTO> orderDTOS=new ArrayList<>();
            for(CustomerOrder o:orders){
                OrderDTO ot=new OrderDTO();
                ot.setOrderStatus(o.getOrderStatus());
                ot.setCreatedAt(o.getCreatedAt());
                ot.setCustomerId(o.getCustomerId());
                ot.setTotalPrice(o.getTotalPrice());
                ot.setUpdatedAt(o.getUpdatedAt());
                updateOrderItem(ot, o.getItemList());
                orderDTOS.add(ot);
            }
            res.setOrderData(orderDTOS);
        }
        ResultStatus rs=new ResultStatus();
        rs.setStatus(Status.SUCCESS);
        rs.setMessage("Order Details fetched successfully");
        res.setStatus(rs);
        return  res;
    }

    private void updateOrderItem(OrderDTO ot, List<OrderItem> itemList) {
        if(itemList!=null && !itemList.isEmpty()){
            List<OrderItemDTO> itemDTOS=new ArrayList<>();
            for(OrderItem it:itemList){
                OrderItemDTO itemDTO=new OrderItemDTO();
                itemDTO.setPrice(it.getPrice());
                itemDTO.setSku(it.getSku());
                itemDTO.setProductId(it.getProductId());
                itemDTO.setQuantity(it.getQuantity());
                itemDTOS.add(itemDTO);
            }
            ot.setItemList(itemDTOS);
        }
    }
}
