package com.ecom.operador.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Instant createdAt = Instant.now();

  private BigDecimal total;

  private String status; // CREATED, PAID, CANCELLED

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<OrderItem> items = new ArrayList<>();

  // getters/setters
  public Long getId() { return id; }
  public Instant getCreatedAt() { return createdAt; }
  public BigDecimal getTotal() { return total; }
  public void setTotal(BigDecimal total) { this.total = total; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public List<OrderItem> getItems() { return items; }
  public void setItems(List<OrderItem> items) { this.items = items; }
}