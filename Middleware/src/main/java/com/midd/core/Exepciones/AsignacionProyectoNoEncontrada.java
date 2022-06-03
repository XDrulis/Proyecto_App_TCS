package com.midd.core.Exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Asignación de proyecto no encontrada")
public class AsignacionProyectoNoEncontrada extends RuntimeException{
    
    public AsignacionProyectoNoEncontrada(String mensaje){
        super(mensaje);
    }

}
