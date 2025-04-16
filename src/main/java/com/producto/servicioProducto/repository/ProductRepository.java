package com.producto.servicioProducto.repository;

import com.producto.servicioProducto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
