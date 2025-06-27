package com.cpb.Service.pedidos;

import com.cpb.Model.DTO.response.ResponseJsonGeneric;

public interface PedidosService {
    ResponseJsonGeneric getAllPedidos();

    ResponseJsonGeneric getAllPedidosPage(int page);

    ResponseJsonGeneric getPedidoById(Long id);

    ResponseJsonGeneric getPedidoByEmail(String email);
}
