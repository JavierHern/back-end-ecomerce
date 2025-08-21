package com.ecom.buscador.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "products", indexes = {
    @Index(name="idx_products_sku", columnList = "sku", unique = true),
    @Index(name="idx_products_name", columnList = "name"),
    @Index(name="idx_products_category", columnList = "category")
})
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String sku;

  @NotBlank
  private String name;

  private String description;
  private String brand;

  @NotNull @Min(0)
  private Integer stock;

  @NotNull
  private BigDecimal price;

  private String category;

  private Instant createdAt = Instant.now();

  // getters and setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getSku() { return sku; }
  public void setSku(String sku) { this.sku = sku; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public String getBrand() { return brand; }
  public void setBrand(String brand) { this.brand = brand; }
  public Integer getStock() { return stock; }
  public void setStock(Integer stock) { this.stock = stock; }
  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }
  public String getCategory() { return category; }
  public void setCategory(String category) { this.category = category; }
  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}