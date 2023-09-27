package com.leydy.usuarioservice.controller;

import com.leydy.usuarioservice.Modelos.Carro;
import com.leydy.usuarioservice.Modelos.Moto;
import com.leydy.usuarioservice.entidades.Usuario;
import com.leydy.usuarioservice.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>>listarUsuarios(){
        List<Usuario>usuarios=usuarioService.getAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id")int id){
        Usuario usuario=usuarioService.getUsuarioById(id);
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @PostMapping
    public ResponseEntity<Usuario>guardarUsuario(@RequestBody Usuario usuario){
        Usuario nuevousuario=usuarioService.save(usuario);
        return ResponseEntity.ok(nuevousuario);
    }
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>>getCarros(@PathVariable("usuarioId")int usuarioId){
        Usuario usuario=usuarioService.getUsuarioById(usuarioId);
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        List <Carro>carros=usuarioService.getCarros(usuarioId);
        return ResponseEntity.ok(carros);
    }
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>>getMotos(@PathVariable("usuarioId")int usuarioId){
        Usuario usuario=usuarioService.getUsuarioById(usuarioId);
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        List <Moto>motos=usuarioService.getMotos(usuarioId);
        return ResponseEntity.ok(motos);
    }
    //feignClient
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro>guardarCarro(@PathVariable("usuarioId")int usuarioId,@RequestBody Carro carro){
        Carro nuevoCarro=usuarioService.saveCarro(usuarioId,carro);
        return ResponseEntity.ok(nuevoCarro);
    }
    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto>guardarCarro(@PathVariable("usuarioId")int usuarioId,@RequestBody Moto moto){
        Moto nuevoMoto=usuarioService.saveMoto(usuarioId,moto);
        return ResponseEntity.ok(nuevoMoto);
    }
    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>>listarTodosLosVehiculos(@PathVariable("usuarioId")int usuarioId){
        Map<String, Object>resultado=usuarioService.getUsuarioAndVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }

}
