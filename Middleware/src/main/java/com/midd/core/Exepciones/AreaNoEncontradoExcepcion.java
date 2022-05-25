package com.midd.core.Exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Area no encontrado")
public class AreaNoEncontradoExcepcion extends RuntimeException{
	
	public AreaNoEncontradoExcepcion (String msg) {
		super(msg);
	}
}
