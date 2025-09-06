
package com.tesoramobil.cooperacion.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tesoramobil.cooperacion.application.exception.CooperacionNoEncontradaException;
import com.tesoramobil.cooperacion.application.port.in.BuscarCooperacionPorId;
import com.tesoramobil.cooperacion.application.port.out.AportacionQueryPort;
import com.tesoramobil.cooperacion.application.port.out.CooperacionRepositoryPort;
import com.tesoramobil.cooperacion.domain.model.Cooperacion;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ListarCooperacionesPorIdService implements BuscarCooperacionPorId {

	private final CooperacionRepositoryPort repo;

	private final AportacionQueryPort aportacionQueryPort;

	@Override
	public Cooperacion ejecutar(Long id) {

		var coop = repo.findById(id).orElseThrow(() -> new CooperacionNoEncontradaException(id));

		var aportes = Optional.ofNullable(aportacionQueryPort.obtenerPorCooperacion(id)).orElseGet(java.util.Collections::emptyList);
        System.out.println("APORTES: " + aportes.toString());
		coop.cargarAportaciones(aportes);

		
        System.out.println("Cooperacion Completa: : " + coop.toString());
        
        
		return coop;

	}

}