package com.leydy.carroservice.controller;

import com.leydy.carroservice.entidades.Carro;
import com.leydy.carroservice.servicio.CarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {
    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }
    @GetMapping
    public ResponseEntity<List<Carro>> listarCarros(){
        List<Carro>carros=carroService.getAll();
        if(carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Carro> obtenerCarro(@PathVariable("id")int id){
        Carro carro=carroService.getCarroById(id);
        if(carro==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }
    @PostMapping
    public ResponseEntity<Carro>guardarCarro(@RequestBody Carro usuario){
        Carro nuevoCarro=carroService.save(usuario);
        return ResponseEntity.ok(nuevoCarro);
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>>listaCarrrosPorUsuarioId(@PathVariable("usuarioId")int usuarioId){
        List <Carro>carros=carroService.findByUsuarioId(usuarioId);
        if(carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
}
