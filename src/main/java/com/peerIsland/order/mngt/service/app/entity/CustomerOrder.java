package com.peerIsland.order.mngt.service.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    @OneToMany(mappedBy = "customerOrder",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> itemList;

    private Double totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String customerId;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer version;
}
