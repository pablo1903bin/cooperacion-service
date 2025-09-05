


package com.tesoramobil.cooperacion.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tesoramobil.cooperacion.application.port.in.BuscarCooperacionPorId;
import com.tesoramobil.cooperacion.application.port.out.CooperacionRepositoryPort;
import com.tesoramobil.cooperacion.domain.model.Cooperacion;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ListarCooperacionesPorIdService implements BuscarCooperacionPorId{

    private final CooperacionRepositoryPort repo;

    @Override
    public Cooperacion ejecutar(Long id) {
        
        Optional<Cooperacion> res  = repo.findById(id);

        throw new UnsupportedOperationException("Unimplemented method 'ejecutar'");
    }

    
}