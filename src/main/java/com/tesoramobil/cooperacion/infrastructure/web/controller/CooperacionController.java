package com.tesoramobil.cooperacion.infrastructure.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.tesoramobil.cooperacion.application.port.in.BuscarCooperacionPorId;
import com.tesoramobil.cooperacion.application.port.in.CrearCooperacionUseCase;
import com.tesoramobil.cooperacion.application.port.in.ListarCooperacionesDetalladasUseCase;
import com.tesoramobil.cooperacion.application.port.in.ListarCooperacionesResumidas;
import com.tesoramobil.cooperacion.infrastructure.web.Responses;
import com.tesoramobil.cooperacion.infrastructure.web.dto.ApiResponse;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CooperacionCompleta;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CooperacionConAportacionesResponse;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CooperacionResponse;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CooperacionResumenResponse;
import com.tesoramobil.cooperacion.infrastructure.web.dto.CrearCooperacionRequest;
import com.tesoramobil.cooperacion.infrastructure.web.mapper.CooperacionWebMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/cooperaciones", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name = "Cooperaciones", description = "Operaciones relacionadas con cooperaciones escolares o comunitarias")
public class CooperacionController {

	private final CrearCooperacionUseCase crearCooperacion;
	private final ListarCooperacionesDetalladasUseCase listarCoopsDetalladas;
	private final ListarCooperacionesResumidas listarCooperacionesResumidas;
	private final BuscarCooperacionPorId buscarCooperacionPorId;
	private final CooperacionWebMapper webMapper;

	@GetMapping("/all")
	@Operation(summary = "Listado de cooperaciones detalladas", description = "Obtiene cooperaciones con todos sus detalles (incluye aportaciones).")
	@ApiResponses({
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CooperacionConAportacionesResponse.class)))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error interno") })
	public ResponseEntity<ApiResponse<List<CooperacionConAportacionesResponse>>> getAllConAportaciones() {
		
		var data = listarCoopsDetalladas.ejecutar();
		
		return ResponseEntity.ok(ApiResponse.ok(data, "Cooperaciones encontradas"));
		
	}

	@GetMapping("/resumen")
	@Operation(summary = "Listado resumido de cooperaciones", description = "Obtiene cooperaciones con datos mínimos (título, rango de fechas, progreso, etc.).")
	@ApiResponses({
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CooperacionResumenResponse.class)))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error interno") })
	public ResponseEntity<ApiResponse<List<CooperacionResumenResponse>>> getResumen() {
		
		var dominios = listarCooperacionesResumidas.ejecutar();
		
		var resumen = webMapper.toResumenList(dominios);
		
		return Responses.ok(resumen, "Cooperaciones (resumen)");
		
	}
	
	@GetMapping("byId/{id}")
	@Operation(
	    summary = "Obtener cooperación por ID",
	    description = "Devuelve los datos resumidos de una cooperación según su identificador."
	)
	@ApiResponses({
	    @io.swagger.v3.oas.annotations.responses.ApiResponse(
	        responseCode = "200",
	        description = "OK",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = CooperacionCompleta.class)
	        )
	    ),
	    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "No encontrada"),
	    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error interno")
	})
	public ResponseEntity<ApiResponse<CooperacionCompleta>> getById(@PathVariable Long id) {

	  return Responses.ok( webMapper.toCooperacionCompleta(  buscarCooperacionPorId.ejecutar(id) ) , "Todo ok ");
	  
	}

	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Crear cooperación", description = "Crea una nueva cooperación con los datos proporcionados.")
	@ApiResponses({
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Creada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CooperacionResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Petición inválida"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error interno") })
	public ResponseEntity<ApiResponse<CooperacionResponse>> create(@Valid @RequestBody CrearCooperacionRequest req) {
		
		var creada = crearCooperacion.ejecutar(webMapper.toDomain(req));
		
		var body = ApiResponse.created(webMapper.toResponse(creada), "Cooperación creada");

		URI location = URI.create("/cooperaciones/" + body.getData().id());

		return ResponseEntity.created(location).body(body);
	}
}
