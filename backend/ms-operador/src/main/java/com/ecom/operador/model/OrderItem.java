package com.ecom.operador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long productId;
  private String productName;
  private String sku;

  private Integer quantity;
  private BigDecimal unitPrice;
  private BigDecimal lineTotal;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  @JsonIgnore
  private Order order;

  // getters/setters
  public Long getId() { return id; }
  public Long getProductId() { return productId; }
  public void setProductId(Long productId) { this.productId = productId; }
  public String getProductName() { return productName; }
  public void setProductName(String productName) { this.productName = productName; }
  public String getSku() { return sku; }
  public void setSku(String sku) { this.sku = sku; }
  public Integer getQuantity() { return quantity; }
  public void setQuantity(Integer quantity) { this.quantity = quantity; }
  public BigDecimal getUnitPrice() { return unitPrice; }
  public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
  public BigDecimal getLineTotal() { return lineTotal; }
  public void setLineTotal(BigDecimal lineTotal) { this.lineTotal = lineTotal; }
  public Order getOrder() { return order; }
  public void setOrder(Order order) { this.order = order; }
}