package com.midd.core.Exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Activo no encontrado")
public class ActivoNoEncontradoExcepcion extends RuntimeException{
	public ActivoNoEncontradoExcepcion (String msg) {
		super(msg);
	}
}
