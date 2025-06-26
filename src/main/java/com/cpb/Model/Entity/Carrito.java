package com.cpb.Model.Entity;

import com.cpb.Model.Enums.EstadoCarritoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carrito implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "carrito_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carritoId;
    @Column(name = "precio")
    private double precio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "carrito",cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
    @OneToMany(mappedBy = "carrito",cascade = CascadeType.ALL)
    private List<CarritoProducto> productos;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoCarritoEnum estado;
}
