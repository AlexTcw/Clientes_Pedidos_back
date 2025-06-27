package com.cpb.Controller;

import com.cpb.Model.DTO.consume.ConsumeJsonGeneric;
import com.cpb.Model.DTO.response.ResponseJsonGeneric;
import com.cpb.Service.cupones.CuponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/cupon"})
public class CuponController {

    private final CuponService cuponService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseJsonGeneric> findAllCupones() {
        return ResponseEntity.ok(cuponService.findAllCupones());
    }

    @GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseJsonGeneric> findCuponByID(@PathVariable("id") Long id){
        if (id == null || id <= 0){
            throw new IllegalArgumentException("ID invalido");
        }
        return ResponseEntity.ok(cuponService.findCuponByid(id));
    }

    @GetMapping(value = "codigo/{codigo}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseJsonGeneric> findCuponByID(@PathVariable("codigo") String codigo){
        if (codigo == null || codigo.isEmpty()){
            throw new IllegalArgumentException("codigo invalido");
        }
        return ResponseEntity.ok(cuponService.findCuponByCodigo(codigo));
    }

    @GetMapping(value = "activo",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseJsonGeneric> findCuponesActivos() {
        return ResponseEntity.ok(cuponService.findCuponesActivos());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseJsonGeneric> saveCupon(@RequestBody ConsumeJsonGeneric consume){
        if (consume == null){
            throw new IllegalArgumentException("Consume invalido");
        }
        if (consume.data() == null){
            throw new IllegalArgumentException("data invalido");
        }
        return ResponseEntity.ok(cuponService.createCupon(consume));
    }

    @DeleteMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseJsonGeneric> deleteCupon(@PathVariable("id") Long cuponId){
        if (cuponId == null || cuponId <= 0){
            throw new IllegalArgumentException("ID invalido");
        }
        return ResponseEntity.ok(cuponService.deleteCuponById(cuponId));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseJsonGeneric> updateCupon(@RequestBody ConsumeJsonGeneric consume){
        if (consume == null){
            throw new IllegalArgumentException("Consume invalido");
        }
        if (consume.data() == null){
            throw new IllegalArgumentException("data invalido");
        }
        if (!consume.data().containsKey("id")){
            throw new IllegalArgumentException("id invalido");
        }

        return ResponseEntity.ok(cuponService.updateCupon(consume));
    }
}
