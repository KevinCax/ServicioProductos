package com.producto.servicioProducto.mapper;

import com.producto.servicioProducto.dto.ProductDto;
import com.producto.servicioProducto.model.Product;

public class ProductMapper {

    // Convierte de entidad a DTO
    public static ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductDto dto = new ProductDto();
        dto.setId(Math.toIntExact(product.getId()));
        dto.setNombre(product.getName());
        dto.setDescripcion(product.getDescription());
        dto.setPrecio(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }

    // Convierte de DTO a entidad
    public static Product toEntity(ProductDto dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        // El ID se asigna automáticamente al crear, pero lo copiamos si existe
        product.setId(Long.valueOf(dto.getId()));
        product.setName(dto.getNombre());
        product.setDescription(dto.getDescripcion());
        product.setPrice(dto.getPrecio());
        product.setStock(dto.getStock());
        return product;
    }
}
