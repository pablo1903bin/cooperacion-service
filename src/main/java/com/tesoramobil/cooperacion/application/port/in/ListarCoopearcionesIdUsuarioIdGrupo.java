package com.tesoramobil.cooperacion.application.port.in;

import java.util.List;

import com.tesoramobil.cooperacion.domain.model.Cooperacion;

public interface ListarCoopearcionesIdUsuarioIdGrupo {
	
	 List<Cooperacion> ejecutar(Long userId, Long groupId);

}
