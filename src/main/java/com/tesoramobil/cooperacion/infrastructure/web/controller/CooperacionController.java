package com.tesoramobil.cooperacion.infrastructure.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesoramobil.cooperacion.application.port.in.CrearCooperacionUseCase;
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

    private final CrearCooperacionUseCase crearUseCase;   // puerto de entrada
    
    private final CooperacionWebMapper webMapper;


    @PostMapping
    public ResponseEntity<ApiResponse<CooperacionResponse>> create(@Valid @RequestBody CrearCooperacionRequest req) {
    	
    	System.out.println("Llamando al controller....");
    	System.out.println("REQUEST: " + req );
    	System.out.println("Mapper: " + webMapper.toDomain(req) );
    	
        var creada = crearUseCase.ejecutar(webMapper.toDomain(req));
        
        var body = ApiResponse.created(webMapper.toResponse(creada), "Cooperación creada");
        
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
        
    }
    
}