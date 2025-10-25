package com.peerIsland.order.mngt.service.app.controler;

import com.peerIsland.order.mngt.service.app.dto.*;
import com.peerIsland.order.mngt.service.app.service.CancelOrderService;
import com.peerIsland.order.mngt.service.app.service.CreateOrderService;
import com.peerIsland.order.mngt.service.app.service.GetOrderDetailsService;
import com.peerIsland.order.mngt.service.app.service.UpdateOrderStatus;
import com.peerIsland.order.mngt.service.app.service.impl.UpdateOrderStatusImpl;
import com.peerIsland.order.mngt.service.app.util.OrderStatusScheduler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perIsland/order/mngt/v1")
@Tag(name = "PeerIsland-Order-Service",description = "PeerIsland Order Service Controller")
public class OrderServiceRestApi {

    @Autowired
    private GetOrderDetailsService getOrderDetailsService;
    @Autowired
    private CreateOrderService createOrderService;
    @Autowired
    private CancelOrderService cancelOrderService;
    @Autowired
    private UpdateOrderStatus updateOrderStatus;
    @Autowired
    private OrderStatusScheduler orderStatusScheduler;

    @GetMapping
    public ResponseEntity<GetOrderResponse> getAllOrder(@RequestHeader String customerId, @RequestParam(value = "status",required = false) String status){
        GetOrderResponse allOrder;
        if(status!=null && !status.isBlank()){
            allOrder=getOrderDetailsService.getAllOrderByStatus(customerId,status);
        }else{
            allOrder = getOrderDetailsService.getAllOrder(customerId);
        }
        return ResponseEntity.ok(allOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderResponse> getOrderById(@RequestHeader String customerId, @PathVariable long orderId){
        GetOrderResponse orderDetailByOrderId = getOrderDetailsService.getOrderDetailByOrderId(customerId, orderId);
        return ResponseEntity.ok(orderDetailByOrderId);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestHeader String customerId, @RequestBody CreateOrderRequest createOrderRequest){
        CreateOrderResponse response= createOrderService.createOrder(customerId,createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/cancel/{orderId}")
    public ResponseEntity<ResultStatus> cancelOrderById(@RequestHeader String customerId,@PathVariable long orderId){
        ResultStatus resultStatus = cancelOrderService.cancelOrderId(orderId, customerId);
        return ResponseEntity.ok(resultStatus);
    }

    @PostMapping("/update/orderStatus")
    public ResponseEntity<ResultStatus> updateOrderStatusByOrderId(@RequestHeader String customerId, @RequestBody UpdateOrderStatusRequest updateOrderStatusRequest){
        ResultStatus resultStatus = updateOrderStatus.updateOrderByStatus(customerId, updateOrderStatusRequest.getOrderId(), updateOrderStatusRequest.getToStatus(), updateOrderStatusRequest.getFromStatus());
        return ResponseEntity.ok(resultStatus);
    }

    @GetMapping("/orderStatusScheduler/start")
    public ResponseEntity<ResultStatus> startScheduler(){
        orderStatusScheduler.start();
        ResultStatus rs=new ResultStatus();
        rs.setStatus(Status.SUCCESS);
        rs.setMessage("OrderStatusScheduler started");
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/orderStatusScheduler/stop")
    public ResponseEntity<ResultStatus> stopScheduler(){
        orderStatusScheduler.stop();
        ResultStatus rs=new ResultStatus();
        rs.setStatus(Status.SUCCESS);
        rs.setMessage("OrderStatusScheduler stopped");
        return ResponseEntity.ok(rs);
    }

}
