package com.tesoramobil.cooperacion.application.port.out;

import com.tesoramobil.cooperacion.domain.model.Cooperacion;
import java.util.*;

/* CooperacionRepositoryPort Es un puerto (interfaz) del dominio que define, lo que el dominio necesita de una “fuente de datos” (BD, API, etc.).*/

public interface CooperacionRepositoryPort {
	
	Cooperacion save(Cooperacion c);

	Optional<Cooperacion> findById(Long id);

	List<Cooperacion> findAll();

	void deleteById(Long id);

	boolean existsByNombre(String nombre);
	
	List<Cooperacion> findAllByUsuarioAndGrupo(Long userId, Long groupId);
	
	
}