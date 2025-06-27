package com.cpb.Controller;

import com.cpb.Model.DTO.response.ResponseJsonGeneric;
import com.cpb.Service.pedidos.PedidosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/pedido"})
public class PedidoController {

    private final PedidosService pedidosService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseJsonGeneric findAllPedidosConProductos() {
        return pedidosService.getAllPedidos();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseJsonGeneric findPedidoById(@PathVariable("id") Long id){
        return pedidosService.getPedidoById(id);
    }

    @GetMapping(value = "email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseJsonGeneric findPedidoByEmail(@PathVariable("email") String email){
        return pedidosService.getPedidoByEmail(email);
    }

    @GetMapping(value = "page/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseJsonGeneric findPedidoByEmail(@PathVariable("page") Integer page){
        if(page == null){
            page = 0;
        }
        page = page - 1;
        return pedidosService.getAllPedidosPage(page);
    }
}
