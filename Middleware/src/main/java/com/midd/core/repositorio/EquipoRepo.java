package com.midd.core.repositorio;

import com.midd.core.modelo.Equipo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepo extends JpaRepository<Equipo, Long>{
	
}