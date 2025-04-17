package com.producto.servicioProducto.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @Id
    private Integer id;
    private String nombre;
    private String descripcion;
    private double precio;
    private Integer stock;
}
