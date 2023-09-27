package com.leydy.motoservice.service;

import com.leydy.motoservice.entidades.Moto;
import com.leydy.motoservice.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MotoService {
    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    public List<Moto> getAll(){
        return motoRepository.findAll();
    }
    public Moto getMotoById(int id){
        return motoRepository.findById(id).orElse(null);
    }
    public Moto save(Moto carro){
        Moto motoNuevo=motoRepository.save(carro);
        return motoNuevo;
    }
    public List<Moto>findByUsuarioId(int usuarioId){
        return motoRepository.findByUsuarioId(usuarioId);
    }
}
