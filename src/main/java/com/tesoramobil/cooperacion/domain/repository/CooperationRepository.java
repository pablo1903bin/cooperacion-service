
package com.tesoramobil.cooperacion.domain.repository;

import java.util.List;
import java.util.Optional;

import com.tesoramobil.cooperacion.domain.model.Cooperacion;

/** El puerto expone operaciones en lenguaje de negocio */
public interface CooperationRepository {

  Cooperacion save(Cooperacion cooperacion);

}
