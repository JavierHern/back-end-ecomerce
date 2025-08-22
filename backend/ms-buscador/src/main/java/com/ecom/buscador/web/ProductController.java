package com.ecom.buscador.web;

import com.ecom.buscador.model.Product;
import com.ecom.buscador.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public Page<Product> search(
      @RequestParam(required = false) String q,
      @RequestParam(required = false) String sku,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String category,
      @RequestParam(required = false) BigDecimal minPrice,
      @RequestParam(required = false) BigDecimal maxPrice,
      @RequestParam(required = false) Integer minStock,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "12") int size,
      @RequestParam(required = false) String sort) {
    return service.search(q, sku, name, category, minPrice, maxPrice, minStock, page, size, sort);
  }

  @GetMapping("/{id}")
  public Product get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product create(@Valid @RequestBody Product p) {
    return service.create(p);
  }

  @PutMapping("/{id}")
  public Product update(@PathVariable Long id, @Valid @RequestBody Product p) {
    return service.update(id, p);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }

  // Internal endpoints for inter-service operations
  @PostMapping("/internal/reserve")
  public Map<String, Object> reserve(@RequestBody Map<String, Object> body) {
    Long productId = Long.valueOf(body.get("productId").toString());
    int quantity = Integer.parseInt(body.get("quantity").toString());
    service.reserveStock(productId, quantity);
    return Map.of("status", "reserved", "productId", productId, "quantity", quantity);
  }

  @PostMapping("/internal/bulk-get")
  public List<Product> bulkGet(@RequestBody List<Long> ids) {
    return ids == null || ids.isEmpty() ? List.of() : ids.stream().map(service::get).toList();
  }
}