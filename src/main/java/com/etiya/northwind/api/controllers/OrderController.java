package com.etiya.northwind.api.controllers;

import com.etiya.northwind.Business.Responses.Employees.EmployeeListResponse;
import com.etiya.northwind.Business.Responses.Orders.OrderListResponse;
import com.etiya.northwind.Business.Abstracts.OrderService;
import com.etiya.northwind.Business.requests.orders.CreateOrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAll")
    public List<OrderListResponse> getAll(){
        return this.orderService.getAll();
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody @Valid OrderListResponse orderListResponse ){
        this.orderService.updateOrder(orderListResponse);
        return ResponseEntity.ok("Order is updated");
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable @Valid int orderId ){
        this.orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order is deleted");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderListResponse> getOrder(@PathVariable @Valid int orderId){
        return ResponseEntity.ok(this.orderService.getOrderById(orderId));
    }

    @PostMapping("/create")
    public ResponseEntity<String > createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest){
        this.orderService.addOrder(createOrderRequest);
        return  ResponseEntity.ok("Order is added");
    }

    @GetMapping("/getAllByPage")
    public ResponseEntity<Page<OrderListResponse>> getAllOrder2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size
    ){
        return ResponseEntity.ok(this.orderService.getAllByPage(page, size));
    }
    @GetMapping("/getAllByPageWithField")
    public ResponseEntity<Page<OrderListResponse>> getAllOrder2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field
    ){
        return ResponseEntity.ok(this.orderService.getAllByPageWithField(page, size,field));
    }
    @GetMapping("/getAllByPageWithOrder")
    public ResponseEntity<Page<OrderListResponse>> getAllOrder2(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(name = "Filtrele") String field,
            @RequestParam(name = "Sırala") String order
    ){
        return ResponseEntity.ok(this.orderService.getAllByPageWithOrder(page, size,field,order));
    }
}
