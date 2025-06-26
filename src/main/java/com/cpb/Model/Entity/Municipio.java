package com.cpb.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "municipio")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Municipio implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "municipio_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long municipioId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "estado_id", nullable = false)
    private Estado estado;
    @OneToMany(mappedBy = "municipio")
    private Set<Colonia> colonias;
    @Column(name = "nombre")
    private String nombre;
}
