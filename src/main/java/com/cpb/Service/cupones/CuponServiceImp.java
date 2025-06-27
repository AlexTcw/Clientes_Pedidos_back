package com.cpb.Service.cupones;

import com.cpb.Model.DTO.consume.ConsumeJsonGeneric;
import com.cpb.Model.DTO.response.ResponseJsonGeneric;
import com.cpb.Model.Entity.Cupon;
import com.cpb.Repository.CuponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CuponServiceImp implements CuponService {

    private final CuponRepository cuponRepository;

    @Override
    public ResponseJsonGeneric findAllCupones(){
        List<String[]> results = cuponRepository.findAllCupones();

        return new ResponseJsonGeneric(populateGenericMap(results));
    }

    @Override
    public ResponseJsonGeneric findCuponByid(Long id){
        if (!cuponRepository.existsCuponByCuponId(id)){
            throw new IllegalArgumentException("Cupon with id " + id + " does not exist");
        }
        return new ResponseJsonGeneric(populateGenericMap(cuponRepository.findAllCuponesById(id)));
    }

    @Override
    public ResponseJsonGeneric findCuponByCodigo(String codigo){
        if (!cuponRepository.existsCuponByCodigo(codigo)){
            throw new IllegalArgumentException("Cupon with codigo " + codigo + " does not exist");
        }
        return new ResponseJsonGeneric(populateGenericMap(cuponRepository.findAllCuponesByCodigo(codigo)));
    }

    @Override
    public ResponseJsonGeneric findCuponesActivos(){
        return new ResponseJsonGeneric(populateGenericMap(cuponRepository.findAllCuponesActivos()));
    }

    @Override
    public ResponseJsonGeneric createCupon(ConsumeJsonGeneric consume) {
        Map<String, Object> data = consume.data();
        Map<String, Object> response = new LinkedHashMap<>();

        // Validar tipo
        if (!data.containsKey("tipo") || !data.containsKey("valor")) {
            throw new IllegalArgumentException("Faltan campos obligatorios: tipo o valor");
        }

        String tipo = data.get("tipo").toString().toUpperCase(); // Normalizar

        if (!tipo.equals("VALOR FIJO") && !tipo.equals("PORCENTAJE")) {
            throw new IllegalArgumentException("Tipo de cupón no válido");
        }

        String codigo;
        do {
            codigo = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        } while (cuponRepository.existsCuponByCodigo(codigo));


        // Crear cupón
        Cupon cupon = new Cupon();
        cupon.setCodigo(codigo);
        cupon.setTipo(tipo);
        cupon.setActivo(true);

        // Fecha de inicio y fin (6 meses después)
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fin = now.plusMonths(6);
        cupon.setFechaInicio(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
        cupon.setFechaFin(Date.from(fin.atZone(ZoneId.systemDefault()).toInstant()));

        // Asignar valor según tipo
        double valor = Double.parseDouble(data.get("valor").toString());

        if (tipo.equals("VALOR FIJO")) {
            cupon.setMonto(valor);
            cupon.setPorcentaje(null);
        } else { // PORCENTAJE
            cupon.setPorcentaje(valor);
            cupon.setMonto(null);
        }

        cuponRepository.save(cupon);

        response.put("codigo", codigo);
        response.put("tipo", tipo);
        response.put("fecha_inicio", cupon.getFechaInicio());
        response.put("fecha_fin", cupon.getFechaFin());
        return new ResponseJsonGeneric(response);
    }

    @Override
    public ResponseJsonGeneric deleteCuponById(Long cuponId) {
        if (!cuponRepository.existsCuponByCuponId(cuponId)) {
            throw new IllegalArgumentException("Cupon with id " + cuponId + " does not exist");
        }
        if (cuponRepository.existsPagoByCuponId(cuponId)>0){
            throw new IllegalArgumentException("Cupon with id " + cuponId + " cannot be deleted because it is already used");
        }
        cuponRepository.deleteById(cuponId);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("clave", cuponId);
        return new ResponseJsonGeneric(response);
    }

    @Override
    public ResponseJsonGeneric updateCupon(ConsumeJsonGeneric consume) {
        var data = consume.data();
        Long id = (Long) data.get("id");
        if (!cuponRepository.existsCuponByCuponId(id)) {
            throw new IllegalArgumentException("Cupon with id " + id + " does not exist");
        }


        Cupon cupon = cuponRepository.findCuponByCuponId(id);
        if (data.containsKey("tipo")) {
            if (!data.containsKey("valor")) {
                throw new IllegalArgumentException("you must provide a valor");
            }
            String tipo = data.get("tipo").toString().toUpperCase();
            if (!tipo.equals("VALOR FIJO") && !tipo.equals("PORCENTAJE")) {
                throw new IllegalArgumentException("Tipo de cupón no válido");
            }
            cupon.setTipo(tipo);
            if (tipo.equals("VALOR FIJO")) {
                cupon.setPorcentaje(null);
                cupon.setMonto((double)data.get("valor"));
            }
            if (tipo.equals("PORCENTAJE")) {
                cupon.setPorcentaje((double)data.get("valor"));
                cupon.setMonto(null);
            }
        }
        if (data.containsKey("activo")) {
            boolean activo = (int) data.get("activo") == 1;
        }

        cuponRepository.save(cupon);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("clave", id);
        return new ResponseJsonGeneric(response);
    }


    private Map<String,Object> populateGenericMap(List<String[]> results){
        List<Map<String, Object>> cupones = new ArrayList<>();

        for (String[] fila : results) {
            Map<String, Object> cupon = new LinkedHashMap<>();
            cupon.put("clave", fila[0]);
            cupon.put("codigo", fila[1]);
            cupon.put("activo", fila[2]);
            cupon.put("fecha_inicio", fila[3]);
            cupon.put("fecha_fin", fila[4]);
            cupon.put("valor", fila[5]);
            cupon.put("tipo", fila[6]);
            cupones.add(cupon);
        }

        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("pedidos", cupones);

        return responseMap;
    }
}
