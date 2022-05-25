package com.midd.core.repositorio;

import com.midd.core.modelo.Edificio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepo extends JpaRepository<Edificio, String>{
	
}
