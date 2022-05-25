package com.midd.core.Exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason= "Asignacion no encontrada")
public class AsigancionNoEncontrada extends RuntimeException{

    public AsigancionNoEncontrada(String mensaje) {
        super(mensaje);
    }
    
}
