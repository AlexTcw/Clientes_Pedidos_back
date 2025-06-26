package com.cpb.Repository;

import com.cpb.Model.Entity.Cupon;
import com.cpb.Model.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuponRepository extends JpaRepository<Cupon,Long> {
}
