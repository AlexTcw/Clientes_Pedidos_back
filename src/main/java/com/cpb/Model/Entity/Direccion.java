package com.cpb.Model.Entity;

import com.cpb.Model.Enums.TipoDirec;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "direccion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Direccion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "direccion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long direccionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colonia_id", nullable = false)
    private Colonia colonia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoDirec tipoDirec;
    @Column(name = "calle")
    private String calle;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "predeterminada")
    private Boolean predeterminada;
}
