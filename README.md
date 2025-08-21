# E-commerce Backend Microservices

A Spring Boot microservices architecture for e-commerce platform featuring service discovery, API gateway, product search, and order management services.

## Architecture

- **Eureka Server**: Service discovery and registration
- **API Gateway**: Single entry point with routing and CORS configuration
- **ms-buscador**: Product search service with advanced filtering and stock management
- **ms-operador**: Order management service with inter-service communication

## Prerequisites

- Java 17
- Maven 3.6+
- Docker and Docker Compose
- MySQL (via Docker Compose)

## Technology Stack

- Spring Boot 3.3.2
- Spring Cloud 2023.0.3
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign
- Spring Data JPA
- MySQL 8.0
- Docker Compose

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd back-end-ecomerce
```

### 2. Start MySQL Databases

```bash
docker-compose up -d
```

This will start two MySQL instances:
- **mysql-buscador**: Port 3307 (for product service)
- **mysql-operador**: Port 3308 (for order service)

### 3. Build the Project

```bash
mvn clean compile
```

### 4. Start Services (in order)

#### Start Eureka Server
```bash
cd backend/eureka-server
mvn spring-boot:run
```
Wait for Eureka to start (http://localhost:8761)

#### Start API Gateway
```bash
cd backend/gateway
mvn spring-boot:run
```

#### Start Product Search Service
```bash
cd backend/ms-buscador
mvn spring-boot:run
```

#### Start Order Service
```bash
cd backend/ms-operador
mvn spring-boot:run
```

## API Endpoints

All endpoints are accessible through the API Gateway at `http://localhost:8080`

### Product Service (ms-buscador)

- `GET /api/products` - Search products with filtering
  - Query parameters: `q`, `sku`, `name`, `brand`, `category`, `minPrice`, `maxPrice`, `minStock`, `page`, `size`, `sort`
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Order Service (ms-operador)

- `POST /api/orders` - Create new order
- `GET /api/orders/{id}` - Get order by ID

## Service Communication

- All services register with Eureka Server
- API Gateway routes requests using service names (`lb://ms-buscador`, `lb://ms-operador`)
- ms-operador communicates with ms-buscador via Feign client for stock validation and reservation
- No hardcoded IPs or ports between services

## Configuration

### Environment Variables

For ms-buscador:
- `BUSCADOR_DB_HOST`: MySQL host (default: localhost)
- `BUSCADOR_DB_PORT`: MySQL port (default: 3307)
- `BUSCADOR_DB_NAME`: Database name (default: buscador)
- `BUSCADOR_DB_USER`: Database user (default: root)
- `BUSCADOR_DB_PASS`: Database password (default: root)

For ms-operador:
- `OPERADOR_DB_HOST`: MySQL host (default: localhost)
- `OPERADOR_DB_PORT`: MySQL port (default: 3308)
- `OPERADOR_DB_NAME`: Database name (default: operador)
- `OPERADOR_DB_USER`: Database user (default: root)
- `OPERADOR_DB_PASS`: Database password (default: root)

## CORS Configuration

CORS is enabled globally in the API Gateway with permissive settings for development:
- All origins: `*`
- All methods: `*`
- All headers: `*`

## Database Schema

Tables are automatically created via JPA/Hibernate with `ddl-auto: update`.

### ms-buscador tables:
- `products`: Product catalog with SKU, name, description, brand, stock, price, category

### ms-operador tables:
- `orders`: Order header with total and status
- `order_items`: Order line items with product references

## Development Notes

- All microservices run on random ports (`server.port: 0`)
- Services register with Eureka using their application names
- Inter-service calls use Eureka service discovery
- Database connections include `createDatabaseIfNotExist=true` for convenience
- OpenAPI documentation available at each service's `/swagger-ui.html` endpoint

## Future Enhancements

- Authentication and authorization
- Order payment processing
- Product inventory management
- Search performance optimization
- Monitoring and logging
- Integration tests
- CI/CD pipeline