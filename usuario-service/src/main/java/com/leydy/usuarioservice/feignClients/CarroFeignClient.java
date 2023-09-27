package com.leydy.usuarioservice.feignClients;

import com.leydy.usuarioservice.Modelos.Carro;
import com.leydy.usuarioservice.Modelos.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="carro-service",url="http://localhost:8002")
@RequestMapping("/carro")
public interface CarroFeignClient {
    @PostMapping
    public Carro saveCarro(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    public List<Carro> getCarros(@PathVariable("usuarioId")int usuarioId);

}
