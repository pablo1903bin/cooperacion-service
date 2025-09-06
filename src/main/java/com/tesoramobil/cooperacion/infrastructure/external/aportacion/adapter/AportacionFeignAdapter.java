package com.tesoramobil.cooperacion.infrastructure.external.aportacion.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tesoramobil.cooperacion.application.port.out.AportacionQueryPort;
import com.tesoramobil.cooperacion.domain.model.Aportacion;
import com.tesoramobil.cooperacion.infrastructure.external.aportacion.client.AportacionFeignClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AportacionFeignAdapter implements AportacionQueryPort {

    private final AportacionFeignClient client;

    @Override
    public List<Aportacion> obtenerPorCooperacion(Long cooperacionId) {
    	
        var resp = client.obtenerPorCooperacion(cooperacionId);
        
        if (resp == null || !"OK".equalsIgnoreCase(resp.getCodigo())) {

            return List.of();
        }
        
        return resp.getData() == null ? List.of() : resp.getData();
        
    }
    
}
