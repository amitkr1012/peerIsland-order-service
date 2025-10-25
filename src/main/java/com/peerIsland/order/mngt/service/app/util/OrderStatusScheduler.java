package com.peerIsland.order.mngt.service.app.util;

import com.peerIsland.order.mngt.service.app.dao.OrderDao;
import com.peerIsland.order.mngt.service.app.entity.CustomerOrder;
import com.peerIsland.order.mngt.service.app.entity.OrderStatus;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class OrderStatusScheduler {
    private static final Logger LOGGER= LoggerFactory.getLogger(OrderStatusScheduler.class);
    @Autowired
    private OrderDao orderDao;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "order-status-scheduler");
        t.setDaemon(true);
        return t;
    });



    public void start() {
        long initialDelaySeconds = 1L;
        long periodSeconds = 60L;
        scheduler.scheduleAtFixedRate(this::promotePendingOrders, initialDelaySeconds, periodSeconds, TimeUnit.SECONDS);
        LOGGER.info("OrderStatusScheduler started ....");
    }

    public void stop() {
        scheduler.shutdownNow();
    }

    private void promotePendingOrders() {
        try {
            List<CustomerOrder> pending = orderDao.findAllCustomerOrderByOrderStatus(OrderStatus.PENDING);
            if (pending.isEmpty()) return;
            for (CustomerOrder o : pending) {
                int promoted = orderDao.updateOrderStatusByOrderId(o.getOrderId(), OrderStatus.PROCESSING, OrderStatus.PENDING, Instant.now());
                if (promoted==1) {
                    LOGGER.info("Promoted Order {} to PROCESSING", o.getOrderId());
                }
            }
        } catch (Exception e) {
            // robust handling: log and continue — do not let scheduler die
            LOGGER.error("Exception while promoting  order from pending", e);
        }
    }
}

