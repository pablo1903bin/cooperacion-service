package com.tesoramobil.cooperacion.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.tesoramobil.cooperacion.clients.AportacionClient;
import com.tesoramobil.cooperacion.dtos.Aportacion;
import com.tesoramobil.cooperacion.dtos.CooperationConAportacionesDTO;
import com.tesoramobil.cooperacion.entities.CooperationEntity;
import com.tesoramobil.cooperacion.repositories.CooperationRepository;
import com.tesoramobil.cooperacion.services.CooperationService;

@Service
public class CooperationServiceImpl implements CooperationService {

    @Autowired
    AportacionClient aportacionClient;

    @Autowired
    CooperationRepository cooperationRepository;

    @Override
    public Optional<CooperationConAportacionesDTO> findDetalleById(Long id) {
        Optional<CooperationEntity> coopOpt = cooperationRepository.findById(id);

        if (coopOpt.isPresent()) {
            List<Aportacion> aportaciones = aportacionClient.obtenerPorCooperacion(id).getData();
            CooperationConAportacionesDTO respuesta = new CooperationConAportacionesDTO(coopOpt.get(), aportaciones);
            return Optional.of(respuesta);
        }

        return Optional.empty();
    }

    @Override
    public List<CooperationEntity> findAll() {
        return cooperationRepository.findAll();
    }

    @Override
    public Optional<CooperationEntity> findById(Long id) {
        Optional<CooperationEntity> coopOpt = cooperationRepository.findById(id);

        if (coopOpt.isPresent()) {
            CooperationEntity cooperation = coopOpt.get();
            List<Aportacion> aportaciones = aportacionClient.obtenerPorCooperacion(id).getData();
            System.out.println("🔐 Respuesta del servicio de aportaciones: " + aportaciones);
            cooperation.setAportaciones(aportaciones);
            return Optional.of(cooperation);
        }

        return Optional.empty();
    }

    @Override
    public CooperationEntity save(CooperationEntity cooperation) {
        return cooperationRepository.save(cooperation);
    }

    @Override
    public CooperationEntity update(Long id, CooperationEntity cooperation) {
        Optional<CooperationEntity> existing = cooperationRepository.findById(id);
        if (existing.isPresent()) {
            cooperation.setId(id);
            return cooperationRepository.save(cooperation);
        }
        throw new IllegalArgumentException("Cooperación no encontrada con ID: " + id);
    }

    @Override
    public void deleteById(Long id) {
        if (cooperationRepository.existsById(id)) {
            cooperationRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cooperación no encontrada con ID: " + id);
        }
    }

    @Override
    public List<CooperationEntity> findByGroupId(Integer groupId) {
        return cooperationRepository.findByGroupId(groupId);
    }

    @Override
    public List<CooperationEntity> findActiveByGroupId(Integer groupId) {
        return cooperationRepository.findByGroupIdAndEstado(groupId, "activa");
    }

}