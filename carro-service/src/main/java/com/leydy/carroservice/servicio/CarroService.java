package com.leydy.carroservice.servicio;

import com.leydy.carroservice.entidades.Carro;
import com.leydy.carroservice.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }
    public List<Carro> getAll(){
        return carroRepository.findAll();
    }
    public Carro getCarroById(int id){
        return carroRepository.findById(id).orElse(null);
    }
    public Carro save(Carro carro){
        Carro carroNuevo=carroRepository.save(carro);
        return carroNuevo;
    }
    public List<Carro>findByUsuarioId(int usuarioId){
        return carroRepository.findByUsuarioId(usuarioId);
    }

}
