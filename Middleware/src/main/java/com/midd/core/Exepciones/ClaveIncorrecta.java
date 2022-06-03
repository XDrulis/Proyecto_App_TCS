package com.midd.core.Exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "ClaveIncorrecta")
public class ClaveIncorrecta extends RuntimeException{
	
	public ClaveIncorrecta(String message) {
		super(message);
	}
}
