package com.tesoramobil.cooperacion.infrastructure.persistence.jpa.adapter;


import com.tesoramobil.cooperacion.application.port.out.CooperacionRepositoryPort;
import com.tesoramobil.cooperacion.domain.model.Cooperacion;
import com.tesoramobil.cooperacion.infrastructure.persistence.jpa.mapper.CooperacionMapper;
import com.tesoramobil.cooperacion.infrastructure.persistence.jpa.repository.CooperationJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CooperacionJpaAdapter implements CooperacionRepositoryPort {

    private final CooperationJpaRepository jpa;
    private final CooperacionMapper mapper;

    @Override
    public Cooperacion save(Cooperacion c) {
        var saved = jpa.save(mapper.toEntity(c));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Cooperacion> findById(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Cooperacion> findAll() {
        return mapper.toDomainList(jpa.findAll());
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

	@Override
	public boolean existsByNombre(String nombre) {
		// TODO Auto-generated method stub
		return false;
	}


}