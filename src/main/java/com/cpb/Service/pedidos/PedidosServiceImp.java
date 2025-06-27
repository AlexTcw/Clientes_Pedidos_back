package com.cpb.Service.pedidos;

import com.cpb.Model.DTO.response.ResponseJsonGeneric;
import com.cpb.Model.Entity.Pedido;
import com.cpb.Repository.ClienteRepository;
import com.cpb.Repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PedidosServiceImp implements PedidosService {

    private final PedidoRepository pedidosRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public ResponseJsonGeneric getAllPedidos() {
        List<String[]> resultados = pedidosRepository.findAllPedidosConProductos();
        return new ResponseJsonGeneric(populateGenericMap(resultados));
    }

    @Override
    public ResponseJsonGeneric getAllPedidosPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Object[]> resultsPage = pedidosRepository.findAllPedidosConProductos(pageable);
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("page", pageNumber+1);
        responseMap.put("size", 10);
        responseMap.put("total", resultsPage.getTotalElements());
        responseMap.put("total_pages", resultsPage.getTotalPages());
        responseMap.put("data", resultsPage.getContent());

        return new ResponseJsonGeneric(responseMap);
    }

    @Override
    public ResponseJsonGeneric getPedidoById(Long id){
        if(id==null || id <= 0){
            throw new IllegalArgumentException("id cannot be null or negative");
        }
        if(!pedidosRepository.existsById(id)){
            throw new IllegalArgumentException("id not found");
        }
        return new ResponseJsonGeneric(populateGenericMap(pedidosRepository.findPedidoByID(id)));
    }

    @Override
    public ResponseJsonGeneric getPedidoByEmail(String email){
        if(email==null || email.isEmpty()){
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (!clienteRepository.existsClienteByEmail(email)){
            throw new IllegalArgumentException("email not found");
        }
        var results = pedidosRepository.findPedidoByEmail(email);
        if(results.isEmpty()){
            throw new IllegalArgumentException("Pedidos for the given email not found");
        }
        return new ResponseJsonGeneric(populateGenericMap(results));
    }

    private Map<String,Object> populateGenericMap(List<String[]> results){
        List<Map<String, Object>> pedidos = new ArrayList<>();

        for (String[] fila : results) {
            Map<String, Object> pedido = new LinkedHashMap<>();
            pedido.put("clave", fila[0]);
            pedido.put("cliente", fila[1]);
            pedido.put("cupon", fila[2]);
            pedido.put("fecha", fila[3]);
            pedido.put("estado", fila[4]);
            pedido.put("total", fila[5]);
            pedido.put("productos", fila[6]);  // el string concatenado de productos
            pedidos.add(pedido);
        }

        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("pedidos", pedidos);

        return responseMap;
    }



}
