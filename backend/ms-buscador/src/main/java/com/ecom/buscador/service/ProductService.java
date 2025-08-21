package com.ecom.buscador.service;

import com.ecom.buscador.model.Product;
import com.ecom.buscador.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ProductService {

  private final ProductRepository repo;

  public ProductService(ProductRepository repo) {
    this.repo = repo;
  }

  public Page<Product> search(String q, String sku, String name, String brand, String category,
                              BigDecimal minPrice, BigDecimal maxPrice, Integer minStock,
                              int page, int size, String sort) {
    Specification<Product> spec = Specification.where(ProductSpecifications.text(q))
        .and(ProductSpecifications.hasSku(sku))
        .and(ProductSpecifications.hasName(name))
        .and(ProductSpecifications.hasBrand(brand))
        .and(ProductSpecifications.inCategory(category))
        .and(ProductSpecifications.priceBetween(minPrice, maxPrice))
        .and(ProductSpecifications.stockAtLeast(minStock));

    Sort s = Sort.by("id").descending();
    if (sort != null && !sort.isBlank()) {
      String[] parts = sort.split(",");
      if (parts.length == 2) {
        if ("asc".equalsIgnoreCase(parts[1])) s = Sort.by(Sort.Order.asc(parts[0]));
        else if ("desc".equalsIgnoreCase(parts[1])) s = Sort.by(Sort.Order.desc(parts[0]));
      } else {
        s = Sort.by(sort);
      }
    }

    return repo.findAll(spec, PageRequest.of(page, size, s));
  }

  public Product get(Long id) {
    return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
  }

  public Product create(Product p) { return repo.save(p); }

  public Product update(Long id, Product p) {
    Product db = get(id);
    db.setSku(p.getSku()); db.setName(p.getName()); db.setDescription(p.getDescription());
    db.setBrand(p.getBrand()); db.setPrice(p.getPrice()); db.setStock(p.getStock()); db.setCategory(p.getCategory());
    return repo.save(db);
  }

  public void delete(Long id) { repo.deleteById(id); }

  @Transactional
  public void reserveStock(Long productId, int quantity) {
    Product p = get(productId);
    if (p.getStock() < quantity) throw new IllegalStateException("Insufficient stock for product " + productId);
    p.setStock(p.getStock() - quantity);
    repo.save(p);
  }
}