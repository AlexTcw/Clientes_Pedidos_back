package com.cpb.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "carrito_producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarritoProducto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "carrito_producto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrito_producto_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;
    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id",nullable = false)
    private Producto producto;
    @Column(name = "cantidad")
    private Integer cantidad;

}
