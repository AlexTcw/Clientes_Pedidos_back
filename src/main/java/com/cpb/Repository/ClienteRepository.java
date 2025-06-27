package com.cpb.Repository;

import com.cpb.Model.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsClienteByEmail(String email);
}
