package com.midd.core.modelo;

import javax.persistence.*;

@Entity
@Table(name = "equipo_asi")
public class Equipo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_equipo_asi", nullable = false, updatable = false, unique = true)
    private Long id_asi;

    @Column(name = "tipo_equipo_asi", nullable = false)
    private String tipo_equipo_asi;

    @Column(name="nombre_equipo_asi", nullable = false)
    private String nombre_equipo_asi;
    
    @Column(name="nombre_lider_asi", nullable = false)
    private String nombre_lider;

    @Column(name="nombre_lider_tecnico_asi", nullable = false)
    private String nombre_tecnico;

    @Column(name="miembros_ultimatix_asi", nullable = true)
    private Long[] miembros_ultimatix_asi;

    @Column(name="miembros_nombres_asi", nullable = true)
    private String[] miembros_nombres_asi;

    @Column(name = "descripcion_asi", nullable = false)
    private String descripcion_asi;

    @Column(name="estado_asi", nullable = false)
    private boolean estado_asi;

    public Equipo() {
    }

    public Equipo(Long id_asi, String tipo_equipo_asi, String nombre_equipo_asi, String nombre_lider,
            String nombre_tecnico, Long[] miembros_ultimatix_asi, String[] miembros_nombres_asi, String descripcion_asi,
            boolean estado_asi) {
        this.id_asi = id_asi;
        this.tipo_equipo_asi = tipo_equipo_asi;
        this.nombre_equipo_asi = nombre_equipo_asi;
        this.nombre_lider = nombre_lider;
        this.nombre_tecnico = nombre_tecnico;
        this.miembros_ultimatix_asi = miembros_ultimatix_asi;
        this.miembros_nombres_asi = miembros_nombres_asi;
        this.descripcion_asi = descripcion_asi;
        this.estado_asi = estado_asi;
    }

    public Long getId_asi() {
        return id_asi;
    }

    public void setId_asi(Long id_asi) {
        this.id_asi = id_asi;
    }

    public String getTipo_equipo_asi() {
        return tipo_equipo_asi;
    }

    public void setTipo_equipo_asi(String tipo_equipo_asi) {
        this.tipo_equipo_asi = tipo_equipo_asi;
    }

    public String getNombre_equipo_asi() {
        return nombre_equipo_asi;
    }

    public void setNombre_equipo_asi(String nombre_equipo_asi) {
        this.nombre_equipo_asi = nombre_equipo_asi;
    }

    public String getNombre_lider() {
        return nombre_lider;
    }

    public void setNombre_lider(String nombre_lider) {
        this.nombre_lider = nombre_lider;
    }

    public String getNombre_tecnico() {
        return nombre_tecnico;
    }

    public void setNombre_tecnico(String nombre_tecnico) {
        this.nombre_tecnico = nombre_tecnico;
    }

    public Long[] getMiembros_ultimatix_asi() {
        return miembros_ultimatix_asi;
    }

    public void setMiembros_ultimatix_asi(Long[] miembros_ultimatix_asi) {
        this.miembros_ultimatix_asi = miembros_ultimatix_asi;
    }

    public String[] getMiembros_nombres_asi() {
        return miembros_nombres_asi;
    }

    public void setMiembros_nombres_asi(String[] miembros_nombres_asi) {
        this.miembros_nombres_asi = miembros_nombres_asi;
    }

    public String getDescripcion_asi() {
        return descripcion_asi;
    }

    public void setDescripcion_asi(String descripcion_asi) {
        this.descripcion_asi = descripcion_asi;
    }

    public boolean isEstado_asi() {
        return estado_asi;
    }

    public void setEstado_asi(boolean estado_asi) {
        this.estado_asi = estado_asi;
    }
    
}