package com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.entity.CooperationEntity;

import java.util.List;

@Repository
public interface CooperationJpaRepository extends JpaRepository<CooperationEntity, Long> {

    // Buscar todas las cooperaciones de un grupo específico
    List<CooperationEntity> findByGroupId(Long groupId);

    // Buscar todas las cooperaciones creadas por un usuario
    List<CooperationEntity> findByCreatedBy(Long createdBy);

    // Buscar cooperaciones activas por grupo
    List<CooperationEntity> findByGroupIdAndEstado(Long groupId, String estado);

    // Buscar cooperaciones por nombre (opcional, útil para búsquedas)
    List<CooperationEntity> findByNombreContainingIgnoreCase(String nombre);

    boolean existsByNombreAndGroupId(String nombre, Long groupId); 
}
