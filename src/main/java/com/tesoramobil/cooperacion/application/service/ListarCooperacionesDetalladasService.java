package com.tesoramobil.cooperacion.application.service;

import com.tesoramobil.cooperacion.application.port.in.ListarCooperacionesDetalladasUseCase;
import com.tesoramobil.cooperacion.application.port.out.AportacionQueryPort;
import com.tesoramobil.cooperacion.application.port.out.CooperacionRepositoryPort;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CooperacionConAportacionesResponse;
import com.tesoramobil.cooperacion.mappers.CooperacionDetalleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ListarCooperacionesDetalladasService implements ListarCooperacionesDetalladasUseCase {

	private final CooperacionRepositoryPort repo;
	private final AportacionQueryPort aportacionPort;
	private final CooperacionDetalleMapper detalleMapper;

	@Override
	public List<CooperacionConAportacionesResponse> ejecutar() {
		var cooperaciones = repo.findAll();

		// Nota: esto hace 1 llamada por cooperación (posible N+1). Ver tips abajo.
		return cooperaciones.stream().map(c -> {
			var dto = detalleMapper.toDetalle(c);
			var aportes = aportacionPort.obtenerPorCooperacion(c.getId());
			dto.setAportaciones(aportes); // composición en aplicación
			return dto;
		}).toList();
	}
}