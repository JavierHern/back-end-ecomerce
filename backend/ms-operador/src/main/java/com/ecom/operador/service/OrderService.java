package com.ecom.operador.service;

import com.ecom.operador.client.BuscadorClient;
import com.ecom.operador.model.Order;
import com.ecom.operador.model.OrderItem;
import com.ecom.operador.repo.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

  private final OrderRepository repo;
  private final BuscadorClient buscador;

  public OrderService(OrderRepository repo, BuscadorClient buscador) {
    this.repo = repo;
    this.buscador = buscador;
  }

  @Transactional
  public Order createOrder(List<ItemRequest> items) {
    if (items == null || items.isEmpty()) throw new IllegalArgumentException("Order must contain items");

    // Validate and enrich with current product data
    List<Long> ids = items.stream().map(ItemRequest::productId).toList();
    Map<Long, BuscadorClient.ProductDTO> map = buscador.bulkGet(ids).stream()
        .collect(Collectors.toMap(BuscadorClient.ProductDTO::id, p -> p));

    Order order = new Order();
    order.setStatus("CREATED");

    BigDecimal total = BigDecimal.ZERO;
    for (ItemRequest req : items) {
      BuscadorClient.ProductDTO pd = map.get(req.productId());
      if (pd == null) throw new IllegalStateException("Product not found: " + req.productId());

      // Reserve stock in product service
      buscador.reserve(Map.of("productId", req.productId(), "quantity", req.quantity()));

      OrderItem oi = new OrderItem();
      oi.setOrder(order);
      oi.setProductId(pd.id());
      oi.setSku(pd.sku());
      oi.setProductName(pd.name());
      oi.setQuantity(req.quantity());
      oi.setUnitPrice(pd.price());
      oi.setLineTotal(pd.price().multiply(BigDecimal.valueOf(req.quantity())));
      order.getItems().add(oi);
      total = total.add(oi.getLineTotal());
    }
    order.setTotal(total);
    return repo.save(order);
  }

  public Optional<Order> get(Long id) { return repo.findById(id); }

  public record ItemRequest(Long productId, Integer quantity) {}
}