package com.midd.core.Exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Equipo no encontrado")
public class EquipoNoEncontrado extends RuntimeException{
	public EquipoNoEncontrado (String msg) {
		super(msg);
	}
}
