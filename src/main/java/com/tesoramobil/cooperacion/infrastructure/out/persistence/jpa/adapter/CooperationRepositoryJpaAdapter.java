package com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tesoramobil.cooperacion.domain.model.Cooperacion;
import com.tesoramobil.cooperacion.domain.repository.CooperationRepository;
import com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.entity.CooperationEntity;
import com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.mapper.CooperacionJpaMapper;
import com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.repo.CooperationJpaRepository;

import java.math.BigDecimal;

// Es un adaptador de salida (outbound adapter)   Adaptador, de Infraestructura
// El adapter “traduce” una firma a la otra y hace el mapping
@Repository
public class CooperationRepositoryJpaAdapter implements CooperationRepository {

    private final CooperationJpaRepository jpa;

    public CooperationRepositoryJpaAdapter(CooperationJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Autowired
    CooperacionJpaMapper mapper;

    @Override
    public Cooperacion save(Cooperacion cooperacion) {
        CooperationEntity entity = toEntity(cooperacion);
        CooperationEntity saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    private CooperationEntity toEntity(Cooperacion d) {
        CooperationEntity e = new CooperationEntity();
        e.setId(d.getId());
        e.setNombre(d.getNombre());
        e.setGroupId(d.getGroupId());
        e.setMontoObjetivo(d.getMontoObjetivo());
        e.setMontoActual(d.getMontoActual());
        e.setMontoPorParticipante(d.getMontoPorParticipante());
          // 👇 NUEVO: calcula y persiste el derivado
        e.setMontoRestante( e.getMontoObjetivo().subtract( n(e.getMontoActual()) ) );

        return e;
    }

    private BigDecimal n(BigDecimal x) {
        return x == null ? BigDecimal.ZERO : x;
    }
}