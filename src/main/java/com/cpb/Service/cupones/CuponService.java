package com.cpb.Service.cupones;

import com.cpb.Model.DTO.consume.ConsumeJsonGeneric;
import com.cpb.Model.DTO.response.ResponseJsonGeneric;

public interface CuponService {
    ResponseJsonGeneric findAllCupones();

    ResponseJsonGeneric findCuponByid(Long id);

    ResponseJsonGeneric findCuponByCodigo(String codigo);

    ResponseJsonGeneric findCuponesActivos();

    ResponseJsonGeneric createCupon(ConsumeJsonGeneric consume);

    ResponseJsonGeneric deleteCuponById(Long cuponId);

    ResponseJsonGeneric updateCupon(ConsumeJsonGeneric consume);
}
