package com.cpb.Repository;

import com.cpb.Model.Entity.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuponRepository extends JpaRepository<Cupon,Long> {

    @Query(value = """
        SELECT
            c.cupon_id,
            c.codigo,
            c.activo,
            c.fecha_inicio,
            c.fecha_fin,
            CASE
                WHEN c.tipo = 'VALOR FIJO' THEN c.monto
                WHEN c.tipo = 'PORCENTAJE' THEN c.porcentaje
                ELSE NULL
            END AS valor_aplicable,
            c.tipo
        FROM cupon c
        """, nativeQuery = true)
    public List<String[]> findAllCupones();

    @Query(value = """
        SELECT
            c.cupon_id,
            c.codigo,
            c.activo,
            c.fecha_inicio,
            c.fecha_fin,
            CASE
                WHEN c.tipo = 'VALOR FIJO' THEN c.monto
                WHEN c.tipo = 'PORCENTAJE' THEN c.porcentaje
                ELSE NULL
            END AS valor_aplicable,
            c.tipo
        FROM cupon c
        where c.cupon_id = :cupon_id
        """, nativeQuery = true)
    public List<String[]> findAllCuponesById(@Param("cupon_id") Long cuponId);

    @Query(value = """
        SELECT
            c.cupon_id,
            c.codigo,
            c.activo,
            c.fecha_inicio,
            c.fecha_fin,
            CASE
                WHEN c.tipo = 'VALOR FIJO' THEN c.monto
                WHEN c.tipo = 'PORCENTAJE' THEN c.porcentaje
                ELSE NULL
            END AS valor_aplicable,
            c.tipo
        FROM cupon c
        where c.codigo = :codigo
        """, nativeQuery = true)
    public List<String[]> findAllCuponesByCodigo(@Param("codigo") String codigo);

    @Query(value = """
        SELECT
            c.cupon_id,
            c.codigo,
            c.activo,
            c.fecha_inicio,
            c.fecha_fin,
            CASE
                WHEN c.tipo = 'VALOR FIJO' THEN c.monto
                WHEN c.tipo = 'PORCENTAJE' THEN c.porcentaje
                ELSE NULL
            END AS valor_aplicable,
            c.tipo
        FROM cupon c
        where c.activo = 1
        """, nativeQuery = true)
    public List<String[]> findAllCuponesActivos();

    @Query(value = """
        SELECT COUNT(*)
        FROM pedido p
        WHERE p.cupon_id = :cupon_id
        """, nativeQuery = true)
    int existsPagoByCuponId(@Param("cupon_id") Long cuponId);


    boolean existsCuponByCuponId(Long cuponId);

    boolean existsCuponByCodigo(String codigo);

    Cupon findCuponByCuponId(Long cuponId);
}
