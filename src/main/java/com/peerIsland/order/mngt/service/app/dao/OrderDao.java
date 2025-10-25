package com.peerIsland.order.mngt.service.app.dao;

import com.peerIsland.order.mngt.service.app.entity.CustomerOrder;
import com.peerIsland.order.mngt.service.app.entity.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDao extends JpaRepository<CustomerOrder,Long> {

    @Transactional
    @Modifying
    @Query("update CustomerOrder o set o.orderStatus=:toStatus,o.updatedAt=:updatedAt where o.customerId=:customerId and o.orderId=:orderId and o.orderStatus=:fromStatus")
    public int updateOrderStatusByOrderIdAndCustomerId(OrderStatus toStatus, Long orderId, OrderStatus fromStatus, String customerId,Instant updatedAt);

    @Transactional
    @Modifying
    @Query("update CustomerOrder o set o.orderStatus=:toStatus,o.updatedAt=:updatedAt where o.orderId=:orderId and o.orderStatus=:fromStatus")
    public int updateOrderStatusByOrderId(Long orderId, OrderStatus toStatus, OrderStatus fromStatus, Instant updatedAt);


    public Optional<CustomerOrder> findOrderByOrderId(long orderId);

    public List<CustomerOrder> findAllOrderByCustomerId(String customerId);

    @Query("select o from CustomerOrder o where o.customerId=:customerId and o.orderStatus=:orderStatus")
    public List<CustomerOrder> findAllOrderByCustomerIdAndOrderStatus(String customerId, OrderStatus orderStatus);

    public  List<CustomerOrder> findAllCustomerOrderByOrderStatus(OrderStatus status);
}
