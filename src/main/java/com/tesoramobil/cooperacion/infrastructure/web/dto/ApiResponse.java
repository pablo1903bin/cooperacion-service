package com.tesoramobil.cooperacion.infrastructure.web.dto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
	
    private String codigo;  
    private String mensaje;
    private T data;

    public static <T> ApiResponse<T> ok(T data, String mensaje) {
        return ApiResponse.<T>builder().codigo("OK").mensaje(mensaje).data(data).build();
    }
    
    public static <T> ApiResponse<T> created(T data, String mensaje) {
        return ApiResponse.<T>builder().codigo("OK").mensaje(mensaje).data(data).build();
    }
    
    public static <T> ApiResponse<T> error(String codigo, String mensaje) {
        return ApiResponse.<T>builder().codigo(codigo).mensaje(mensaje).build();
    }
    
}