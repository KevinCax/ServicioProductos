package com.producto.servicioProducto.controllers;

import com.producto.servicioProducto.model.Product;
import com.producto.servicioProducto.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return ResponseEntity.ok(product);
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product productDetails) {
        Product existing = productService.getProductById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        // Asegúrate de copiar solo los campos que deseas actualizar
        existing.setName(productDetails.getName());
        existing.setDescription(productDetails.getDescription());
        existing.setPrice(productDetails.getPrice());
        existing.setStock(productDetails.getStock());
        Product updated = productService.updateProduct(existing);
        return ResponseEntity.ok(updated);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        Product existing = productService.getProductById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        try {
            productService.deleteProduct(existing);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Producto con id " + id + " eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // Actualizar solo el stock de un producto
    @PatchMapping("/stock/{id}")
    public ResponseEntity<Map<String, String>> updateStock(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> payload) {
        Integer quantity = payload.get("stock");
        if (quantity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo 'stock' es obligatorio");
        }
        try {
            productService.updateStock(id, quantity);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Stock del producto con id " + id + " actualizado a " + quantity);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
