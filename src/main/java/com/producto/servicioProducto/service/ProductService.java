package com.producto.servicioProducto.service;

import com.producto.servicioProducto.model.Product;
import com.producto.servicioProducto.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public void updateStock(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("No se encontró producto con id " + id));
        product.setStock(quantity);
        productRepository.save(product);
    }
}
