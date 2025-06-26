package com.cpb.Repository;

import com.cpb.Model.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pedido,Long> {
}
