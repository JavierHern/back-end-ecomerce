package com.ecom.operador.web;

import com.ecom.operador.model.Order;
import com.ecom.operador.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

  private final OrderService service;

  public OrderController(OrderService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Order createOrder(@RequestBody Map<String, List<OrderService.ItemRequest>> body) {
    List<OrderService.ItemRequest> items = body.get("items");
    return service.createOrder(items);
  }

  @GetMapping("/{id}")
  public Order getOrder(@PathVariable Long id) {
    return service.get(id).orElseThrow(() -> new RuntimeException("Order not found"));
  }
}