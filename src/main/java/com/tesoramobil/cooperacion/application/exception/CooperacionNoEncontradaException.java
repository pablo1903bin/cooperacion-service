package com.tesoramobil.cooperacion.application.exception;



public class CooperacionNoEncontradaException extends RuntimeException {
	

    private static final long serialVersionUID = 1L;
    
    
	private final Long id;

    public CooperacionNoEncontradaException(Long id) {
        super(" Cooperación no encontrada: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
