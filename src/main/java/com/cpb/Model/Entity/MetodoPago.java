package com.cpb.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "metodo_pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetodoPago implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "metodo_pago_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metodoPagoId;
    @Column(name = "nombre")
    private String  nombre;
    @Column(name = "descripcion")
    private String  descripcion;
}
