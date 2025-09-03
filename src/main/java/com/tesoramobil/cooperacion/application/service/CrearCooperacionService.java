package com.tesoramobil.cooperacion.application.service;

import com.tesoramobil.cooperacion.application.port.in.CrearCooperacionUseCase;
import com.tesoramobil.cooperacion.application.port.out.CooperacionRepositoryPort;
import com.tesoramobil.cooperacion.domain.model.Cooperacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class CrearCooperacionService implements CrearCooperacionUseCase {


    private final CooperacionRepositoryPort repo;

    @Override
    public Cooperacion ejecutar(Cooperacion nueva) {
    	
    	System.out.println("Ejecutando la clase de implemntacion concreta sobre el caso de uso para crear una cooperacion...");

        return repo.save(nueva);
        
    }

    
}
