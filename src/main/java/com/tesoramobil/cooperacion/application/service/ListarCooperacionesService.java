package com.tesoramobil.cooperacion.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tesoramobil.cooperacion.application.port.in.ListarCooperacionesResumidas;
import com.tesoramobil.cooperacion.application.port.out.CooperacionRepositoryPort;
import com.tesoramobil.cooperacion.domain.model.Cooperacion;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ListarCooperacionesService implements ListarCooperacionesResumidas {
	
	private final CooperacionRepositoryPort repo;

	@Override
	public List<Cooperacion> ejecutar() {
		
		return repo.findAll();
		
	}
	
}
