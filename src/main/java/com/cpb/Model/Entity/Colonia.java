package com.cpb.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "colonia")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Colonia implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "colonia_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coloniaId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipio_id", nullable = false)
    private Municipio municipio;
    @OneToMany(mappedBy = "municipio")
    private Set<Direccion> direcciones;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "codigo_postal")
    private String codigoPostal;
}
