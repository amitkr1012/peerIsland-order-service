package com.peerIsland.order.mngt.service.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;
    private  String sku;
    private long productId;
    private int quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private CustomerOrder customerOrder;
}
