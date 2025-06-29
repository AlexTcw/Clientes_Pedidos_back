package com.cpb.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "producto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    @OneToMany(mappedBy = "producto",cascade = CascadeType.ALL)
    private List<CarritoProducto>  carritos;
    @Column(name = "precio")
    private double precio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "sku")
    private String sku;
}
