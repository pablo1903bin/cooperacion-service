package com.tesoramobil.cooperacion.services;

import java.util.List;
import java.util.Optional;

import com.tesoramobil.cooperacion.dtos.CooperationConAportacionesDTO;
import com.tesoramobil.cooperacion.entities.CooperationEntity;

public interface CooperationService {

    List<CooperationEntity> findAll();

    Optional<CooperationEntity> findById(Long id);

    Optional<CooperationConAportacionesDTO> findDetalleById(Long id);

    CooperationEntity save(CooperationEntity cooperation);

    CooperationEntity update(Long id, CooperationEntity cooperation);

    void deleteById(Long id);

    List<CooperationEntity> findByGroupId(Integer groupId);

    List<CooperationEntity> findActiveByGroupId(Integer groupId);
    
}