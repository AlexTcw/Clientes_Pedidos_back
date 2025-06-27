package com.cpb.Model.Entity;

import com.cpb.Model.Enums.TipoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cupon")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cupon implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cupon_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuponId;
    @OneToMany(mappedBy = "cupon",fetch = FetchType.LAZY)
    private List<Pedido> pedidos;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "porcentaje")
    private Double porcentaje;
    @Column(name = "monto")
    private Double monto;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "activo")
    private Boolean activo;
}
