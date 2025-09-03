package com.tesoramobil.cooperacion.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tesoramobil.cooperacion.infrastructure.persistence.jpa.entity.CooperationEntity;

import java.util.List;

@Repository
public interface CooperationJpaRepository extends JpaRepository<CooperationEntity, Long> {

    // Buscar todas las cooperaciones de un grupo específico
    List<CooperationEntity> findByGroupId(Integer groupId);

    // Buscar todas las cooperaciones creadas por un usuario
    List<CooperationEntity> findByCreatedBy(Long createdBy);

    // Buscar cooperaciones activas por grupo
    List<CooperationEntity> findByGroupIdAndEstado(Integer groupId, String estado);

    // Buscar cooperaciones por nombre (opcional, útil para búsquedas)
    List<CooperationEntity> findByNombreContainingIgnoreCase(String nombre);
}
