package com.tesoramobil.cooperacion.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {


    private String codigo;

    private String mensaje;

    private T data;
    
}
