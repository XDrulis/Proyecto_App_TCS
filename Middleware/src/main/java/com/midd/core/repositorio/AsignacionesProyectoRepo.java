package com.midd.core.repositorio;

import com.midd.core.modelo.AsignacionProyecto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignacionesProyectoRepo extends JpaRepository<AsignacionProyecto,Long> {
    
}
