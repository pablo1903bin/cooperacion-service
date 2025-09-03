package com.tesoramobil.cooperacion.infrastructure.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesoramobil.cooperacion.application.port.in.CrearCooperacionUseCase;
import com.tesoramobil.cooperacion.application.port.in.ListarCooperacionesDetalladasUseCase;
import com.tesoramobil.cooperacion.dtos.CooperacionConAportacionesDTO;
import com.tesoramobil.cooperacion.infrastructure.web.dto.ApiResponse;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CooperacionResponse;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CrearCooperacionRequest;
import com.tesoramobil.cooperacion.infrastructure.web.mapper.CooperacionWebMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cooperaciones")
@RequiredArgsConstructor
@Tag(name = "Cooperaciones", description = "Operaciones relacionadas con cooperaciones escolares o comunitarias")
public class CooperacionController {

    private final CrearCooperacionUseCase crearUseCase; 
    private final ListarCooperacionesDetalladasUseCase listarCoopsDetalladas;
    private final CooperacionWebMapper webMapper;


    @PostMapping
    public ResponseEntity<ApiResponse<CooperacionResponse>> create(@Valid @RequestBody CrearCooperacionRequest req) {
    	
    	
        var creada = crearUseCase.ejecutar(webMapper.toDomain(req));
        
        var body = ApiResponse.created(webMapper.toResponse(creada), "Cooperación creada");
        
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
        
    }
    
    @GetMapping("/detalle")
    public ResponseEntity<ApiResponse<List<CooperacionConAportacionesDTO>>> getAllConAportaciones() {
    	
        var data = listarCoopsDetalladas.ejecutar();
        
        return ResponseEntity.ok(ApiResponse.ok(data, "Cooperaciones encontradas"));
    }

    
}