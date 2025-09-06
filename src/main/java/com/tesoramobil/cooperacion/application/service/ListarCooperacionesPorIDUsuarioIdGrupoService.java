package com.tesoramobil.cooperacion.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tesoramobil.cooperacion.application.port.in.ListarCoopearcionesIdUsuarioIdGrupo;
import com.tesoramobil.cooperacion.application.port.out.AportacionQueryPort;
import com.tesoramobil.cooperacion.application.port.out.CooperacionRepositoryPort;
import com.tesoramobil.cooperacion.domain.model.Cooperacion;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ListarCooperacionesPorIDUsuarioIdGrupoService implements ListarCoopearcionesIdUsuarioIdGrupo {
	
	private final CooperacionRepositoryPort repo;
	
	private final AportacionQueryPort aportacionPort;
	
	
	@Override
	public List<Cooperacion> ejecutar(Long userId, Long groupId) {
		
		var cooperaciones = repo.findAllByUsuarioAndGrupo(userId, groupId);
		
		return cooperaciones.stream().map( cooperacionCompleta -> {
			

			var aportes = aportacionPort.obtenerPorCooperacion(cooperacionCompleta.getId());
			
			cooperacionCompleta.setAportaciones(aportes);
			
	        return cooperacionCompleta;
			
		}).toList();
	    
	}


}
