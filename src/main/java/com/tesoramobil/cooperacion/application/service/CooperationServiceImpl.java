package com.tesoramobil.cooperacion.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesoramobil.cooperacion.application.comand.CrearCooperacionCommand;
import com.tesoramobil.cooperacion.application.mapper.CooperacionCommandMapper;
import com.tesoramobil.cooperacion.domain.client.AportacionClientPort;
import com.tesoramobil.cooperacion.domain.client.UsuarioClientPort;
import com.tesoramobil.cooperacion.domain.model.Cooperacion;
import com.tesoramobil.cooperacion.domain.repository.CooperationRepository;
import com.tesoramobil.cooperacion.infrastructure.in.web.dto.Aportacion;
import com.tesoramobil.cooperacion.infrastructure.in.web.dto.CooperationConAportacionesDTO;
import com.tesoramobil.cooperacion.infrastructure.in.web.dto.UsuarioDto;
import com.tesoramobil.cooperacion.infrastructure.out.client.feign.AportacionClient;
import com.tesoramobil.cooperacion.infrastructure.out.client.feign.UserClient;
import com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.entity.CooperationEntity;
import com.tesoramobil.cooperacion.infrastructure.out.persistence.jpa.repo.CooperationJpaRepository;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CooperationServiceImpl implements CooperationService {

    @Autowired
    AportacionClientPort aportacionClient; // puerto de dominio
    @Autowired
    UsuarioClientPort userClient; // puerto de dominio
    @Autowired
    CooperationRepository cooperationRepository; // puerto de dominio
    @Autowired
    CooperacionCommandMapper cooperacionCommandMapper;

    @Override
    @Transactional
    public Long crear(CrearCooperacionCommand cmd) {

        Cooperacion coop = cooperacionCommandMapper.toDomain(cmd);

        Cooperacion saved = cooperationRepository.save(coop);

        return saved.getId();
    }
}
