package com.leydy.usuarioservice.service;

import com.leydy.usuarioservice.Modelos.Carro;
import com.leydy.usuarioservice.Modelos.Moto;
import com.leydy.usuarioservice.Repository.UsuarioRepository;
import com.leydy.usuarioservice.entidades.Usuario;
import com.leydy.usuarioservice.feignClients.CarroFeignClient;
import com.leydy.usuarioservice.feignClients.MotoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarroFeignClient carroFeignClient;
    @Autowired
    private MotoFeignClient motoFeignClient;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }
    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public Usuario save(Usuario usuario){
        Usuario nuevoUsuario=usuarioRepository.save(usuario);
        return nuevoUsuario;
    }
    public List<Carro>getCarros(int usuarioId){
        List<Carro> carros=restTemplate.getForObject("http://localhost:8002/carro/usuario/"+usuarioId,List.class);
        return carros;
    }
    public List<Moto>getMotos(int usuarioId){
        List<Moto> motos=restTemplate.getForObject("http://localhost:8003/moto/usuario/"+usuarioId,List.class);
        return motos;
    }
    //feignClients
    public Carro saveCarro(int usuarioId,Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro=carroFeignClient.saveCarro(carro);
        return nuevoCarro;
    }
    public Moto saveMoto(int usuarioId,Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevoMoto=motoFeignClient.saveMoto(moto);
        return nuevoMoto;
    }
    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String, Object> resultado=new HashMap<>();
        Usuario usuario=usuarioRepository.findById(usuarioId).orElse(null);
        if(usuario==null){
            resultado.put("mensaje","el usuario no existe");
            return  resultado;
        }
        resultado.put("usuario",usuario);
        List<Carro> carros=carroFeignClient.getCarros(usuarioId);
        if(carros.isEmpty()){
            resultado.put("mensaje","el usuario no tiene carros");

        }else{
            resultado.put("carros",carros);
        }
        List<Moto> motos=motoFeignClient.getMotos(usuarioId);
        if(motos.isEmpty()){
            resultado.put("mensaje","el usuario no tiene motos");

        }else{
            resultado.put("motos",motos);
        }
        return resultado;
    }


}

