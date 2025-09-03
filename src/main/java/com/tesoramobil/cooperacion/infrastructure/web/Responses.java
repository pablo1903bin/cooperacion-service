package com.tesoramobil.cooperacion.infrastructure.web;


import org.springframework.http.*;

import com.tesoramobil.cooperacion.infrastructure.web.dto.ApiResponse;

public final class Responses {
	
    private Responses() {}
    
    public static <T> ResponseEntity<ApiResponse<T>> ok(T data, String msg) {
        return ResponseEntity.ok(ApiResponse.ok(data, msg));
    }
    
    public static <T> ResponseEntity<ApiResponse<T>> created(T data, String msg) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(data, msg));
    }
    
    public static ResponseEntity<ApiResponse<Void>> notFound(String msg) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("NOT_FOUND", msg));
    }
    
    public static ResponseEntity<ApiResponse<Void>> badRequest(String msg) {
        return ResponseEntity.badRequest().body(ApiResponse.error("VALIDATION_ERROR", msg));
    }
    
    public static ResponseEntity<ApiResponse<Void>> error500(String msg) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("ERROR", msg));
    }
    
}
