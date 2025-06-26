package com.cpb.Repository;

import com.cpb.Model.Entity.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<CarritoProducto,Long> {
}
