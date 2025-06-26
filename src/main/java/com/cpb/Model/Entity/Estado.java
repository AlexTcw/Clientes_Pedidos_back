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
@Table(name = "estado")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estado implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "estado_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estadoId;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "estado")
    private Set<Municipio> municipios;
}
