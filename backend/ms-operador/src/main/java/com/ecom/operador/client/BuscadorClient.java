package com.ecom.operador.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@FeignClient(name = "ms-buscador", path = "/api/products")
public interface BuscadorClient {

  @PostMapping("/internal/bulk-get")
  List<ProductDTO> bulkGet(@RequestBody List<Long> ids);

  @PostMapping("/internal/reserve")
  Map<String, Object> reserve(@RequestBody Map<String, Object> body);

  record ProductDTO(Long id, String sku, String name, String description, String brand, Integer stock, BigDecimal price, String category) {}
}