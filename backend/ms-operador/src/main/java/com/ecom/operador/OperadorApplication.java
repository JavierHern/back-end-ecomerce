package com.ecom.operador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OperadorApplication {
  public static void main(String[] args) {
    SpringApplication.run(OperadorApplication.class, args);
  }
}