package com.ecom.buscador.service;

import com.ecom.buscador.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {
  public static Specification<Product> hasSku(String sku) {
    return (root, q, cb) -> sku == null ? null : cb.equal(cb.lower(root.get("sku")), sku.toLowerCase());
  }

  public static Specification<Product> hasName(String name) {
    return (root, q, cb) -> name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
  }

  public static Specification<Product> inCategory(String category) {
    return (root, q, cb) -> category == null ? null : cb.equal(cb.lower(root.get("category")), category.toLowerCase());
  }

  public static Specification<Product> priceBetween(BigDecimal min, BigDecimal max) {
    return (root, q, cb) -> {
      if (min == null && max == null)
        return null;
      if (min == null)
        return cb.lessThanOrEqualTo(root.get("price"), max);
      if (max == null)
        return cb.greaterThanOrEqualTo(root.get("price"), min);
      return cb.between(root.get("price"), min, max);
    };
  }

  public static Specification<Product> stockAtLeast(Integer min) {
    return (root, q, cb) -> min == null ? null : cb.greaterThanOrEqualTo(root.get("stock"), min);
  }

  public static Specification<Product> text(String qStr) {
    return (root, q, cb) -> {
      if (qStr == null || qStr.isBlank())
        return null;
      String like = "%" + qStr.toLowerCase() + "%";
      return cb.or(
          cb.like(cb.lower(root.get("name")), like),
          cb.like(cb.lower(root.get("description")), like),
          cb.like(cb.lower(root.get("sku")), like));
    };
  }
}