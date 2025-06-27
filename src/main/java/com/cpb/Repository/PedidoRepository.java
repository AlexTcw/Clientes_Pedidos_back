package com.cpb.Repository;

import com.cpb.Model.Entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    @Query(value = "SELECT p.pedido_id AS clave, " +
            "c.email AS cliente, " +
            "cup.codigo AS cupon, " +
            "p.fecha_pedido AS fecha, " +
            "p.estado, " +
            "p.total, " +
            "GROUP_CONCAT(prod.nombre SEPARATOR ', ') AS productos " +
            "FROM pedido p " +
            "INNER JOIN cliente c ON c.cliente_id = p.cliente_id " +
            "INNER JOIN carrito car ON car.carrito_id = p.carrito_id " +
            "INNER JOIN cupon cup ON cup.cupon_id = p.cupon_id " +
            "INNER JOIN carrito_producto cp ON cp.carrito_id = car.carrito_id " +
            "INNER JOIN producto prod ON prod.producto_id = cp.producto_id " +
            "GROUP BY p.pedido_id, c.email, cup.codigo, p.fecha_pedido, p.estado, p.total",
            nativeQuery = true)
    List<String[]> findAllPedidosConProductos();

    @Query(
            value = "SELECT p.pedido_id AS clave, " +
                    "c.email AS cliente, " +
                    "cup.codigo AS cupon, " +
                    "p.fecha_pedido AS fecha, " +
                    "p.estado, " +
                    "p.total, " +
                    "GROUP_CONCAT(prod.nombre SEPARATOR ', ') AS productos " +
                    "FROM pedido p " +
                    "INNER JOIN cliente c ON c.cliente_id = p.cliente_id " +
                    "INNER JOIN carrito car ON car.carrito_id = p.carrito_id " +
                    "INNER JOIN cupon cup ON cup.cupon_id = p.cupon_id " +
                    "INNER JOIN carrito_producto cp ON cp.carrito_id = car.carrito_id " +
                    "INNER JOIN producto prod ON prod.producto_id = cp.producto_id " +
                    "GROUP BY p.pedido_id, c.email, cup.codigo, p.fecha_pedido, p.estado, p.total",
            countQuery = "SELECT COUNT(DISTINCT p.pedido_id) FROM pedido p " +
                    "INNER JOIN cliente c ON c.cliente_id = p.cliente_id " +
                    "INNER JOIN carrito car ON car.carrito_id = p.carrito_id " +
                    "INNER JOIN cupon cup ON cup.cupon_id = p.cupon_id " +
                    "INNER JOIN carrito_producto cp ON cp.carrito_id = car.carrito_id " +
                    "INNER JOIN producto prod ON prod.producto_id = cp.producto_id",
            nativeQuery = true
    )
    Page<Object[]> findAllPedidosConProductos(Pageable pageable);


    @Query(value = "SELECT p.pedido_id AS clave, " +
            "c.email AS cliente, " +
            "cup.codigo AS cupon, " +
            "p.fecha_pedido AS fecha, " +
            "p.estado, " +
            "p.total, " +
            "GROUP_CONCAT(prod.nombre SEPARATOR ', ') AS productos " +
            "FROM pedido p " +
            "INNER JOIN cliente c ON c.cliente_id = p.cliente_id " +
            "INNER JOIN carrito car ON car.carrito_id = p.carrito_id " +
            "INNER JOIN cupon cup ON cup.cupon_id = p.cupon_id " +
            "INNER JOIN carrito_producto cp ON cp.carrito_id = car.carrito_id " +
            "INNER JOIN producto prod ON prod.producto_id = cp.producto_id " +
            "where p.pedido_id = :pedido_id " +
            "GROUP BY p.pedido_id, c.email, cup.codigo, p.fecha_pedido, p.estado, p.total",
            nativeQuery = true)
    List<String[]> findPedidoByID(@Param("pedido_id") Long pedido_id);

    @Query(value = "SELECT p.pedido_id AS clave, " +
            "c.email AS cliente, " +
            "cup.codigo AS cupon, " +
            "p.fecha_pedido AS fecha, " +
            "p.estado, " +
            "p.total, " +
            "GROUP_CONCAT(prod.nombre SEPARATOR ', ') AS productos " +
            "FROM pedido p " +
            "INNER JOIN cliente c ON c.cliente_id = p.cliente_id " +
            "INNER JOIN carrito car ON car.carrito_id = p.carrito_id " +
            "INNER JOIN cupon cup ON cup.cupon_id = p.cupon_id " +
            "INNER JOIN carrito_producto cp ON cp.carrito_id = car.carrito_id " +
            "INNER JOIN producto prod ON prod.producto_id = cp.producto_id " +
            "where c.email = :email " +
            "GROUP BY p.pedido_id, c.email, cup.codigo, p.fecha_pedido, p.estado, p.total",
            nativeQuery = true)
    List<String[]> findPedidoByEmail(@Param("email") String email);

}
