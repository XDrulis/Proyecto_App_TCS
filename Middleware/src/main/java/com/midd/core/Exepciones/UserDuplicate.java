package com.midd.core.Exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Usuario ya existe")
public class UserDuplicate extends RuntimeException{
	
	public UserDuplicate(String message) {
		super(message);
	}
}
