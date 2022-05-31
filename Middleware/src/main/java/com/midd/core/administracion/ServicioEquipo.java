package com.midd.core.administracion;

import com.midd.core.repositorio.EquipoRepo;

import java.util.ArrayList;
import java.util.List;

import com.midd.core.Exepciones.EquipoNoEncontrado;
import com.midd.core.modelo.Equipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEquipo {
    
    private final EquipoRepo equipo_Repo;

    @Autowired
    public ServicioEquipo(EquipoRepo equipo_Repo) {
        this.equipo_Repo = equipo_Repo;
    }

    public boolean buscarEquipos(Equipo equipo){
        for (Equipo equipos : buscarTodosEquipos()) {
            if (equipos.getNombre_equipo_asi().equals(equipo.getNombre_equipo_asi())
                && equipos.getTipo_equipo_asi().equals(equipo.getTipo_equipo_asi())) {
                return true;
            }
        }
            
        return false;
    }

    public Equipo agregarEquipo(Equipo equipo){
        equipo.setEstado_asi(true);
        equipo.setMiembros_ultimatix_asi(new Long[0]);
        equipo.setMiembros_nombres_asi(new String[0]);    
        return this.equipo_Repo.save(equipo);
    }

    public Equipo actualizarEquipo(Equipo equipo){
        Equipo lider = this.buscarEquipoId(equipo.getId_asi());
        lider.setNombre_lider(equipo.getNombre_lider());
        lider.setNombre_tecnico(equipo.getNombre_tecnico());
        return this.equipo_Repo.save(lider);
    }

    public void actilizarMiembros(Equipo miembros){
        this.equipo_Repo.save(miembros);
    }

    public Equipo eliminarEquipo(Equipo equipo){
        equipo.setEstado_asi(!equipo.isEstado_asi());
        return this.equipo_Repo.save(equipo);
    }

    public List<Equipo> buscarTodosEquipos(){
        return equipo_Repo.findAll();
    }

    public Equipo buscarEquipoId(Long id){
        return equipo_Repo.findById(id).
        orElseThrow(()-> new EquipoNoEncontrado("El equipo con id: " + id + " no existe"));
    }

    public List<Equipo> buscarNombreEquipo(String nombreEquipo){
        List<Equipo> listaEquipos = new ArrayList<>();
        for (Equipo equipos : buscarTodosEquipos()) {
            if (equipos.getNombre_equipo_asi().equals(nombreEquipo)) {
                listaEquipos.add(equipos);
            }
        }
        return listaEquipos;
    }

    public List<Equipo> listaEquipo(String nombre){
        List<Equipo> listaEquipos = new ArrayList<>();
        for (Equipo equipo : buscarTodosEquipos()) {
            if (equipo.getTipo_equipo_asi().equals(nombre)) {
                listaEquipos.add(equipo);
            }
        }
        return listaEquipos;
    }

    public Equipo buscarEquipoMio(Long id){
        Equipo mio = this.buscarEquipoId(id);
        return mio;
    }

}