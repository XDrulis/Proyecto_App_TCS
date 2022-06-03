package com.midd.core.modelo;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asignacion_proyecto_asi")
public class AsignacionProyecto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_asignacion_proyecto_asi", nullable = false, updatable = false, unique = true)
    private Long id_asignacion_proyecto_asg;

    @Column(name = "id_equipo_asi", nullable = false)
    private Long id_equipo_asi;

    @Column(name="ultimatix_asi", nullable = false)
    private Long ultimatix_asi;

    @Column(name = "asignacion_asi", nullable = false)
    private int asignacion;

    @Column(name = "fecha_inicio_asi", nullable = false)
    private Date fecha_inicio;

    @Column(name = "fecha_inicio_fin", nullable = false)
    private Date fecha_fin;

    @Column(name = "fecha_baja_asi", nullable = true)
    private Date fecha_baja;
    
    @Column(name = "estado_asi", nullable = true)
    private Boolean estado;

    public AsignacionProyecto(){}

    public AsignacionProyecto(Long id_asignacion_proyecto_asg, Long id_equipo_asi, Long ultimatix_asi, int asignacion,
            Date fecha_inicio, Date fecha_fin, Date fecha_baja, Boolean estado) {
        this.id_asignacion_proyecto_asg = id_asignacion_proyecto_asg;
        this.id_equipo_asi = id_equipo_asi;
        this.ultimatix_asi = ultimatix_asi;
        this.asignacion = asignacion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.fecha_baja = fecha_baja;
        this.estado = estado;
    }

    public Long getId_asignacion_proyecto_asg() {
        return id_asignacion_proyecto_asg;
    }

    public void setId_asignacion_proyecto_asg(Long id_asignacion_proyecto_asg) {
        this.id_asignacion_proyecto_asg = id_asignacion_proyecto_asg;
    }

    public Long getId_equipo_asi() {
        return id_equipo_asi;
    }

    public void setId_equipo_asi(Long id_equipo_asi) {
        this.id_equipo_asi = id_equipo_asi;
    }

    public Long getUltimatix_asi() {
        return ultimatix_asi;
    }

    public void setUltimatix_asi(Long ultimatix_asi) {
        this.ultimatix_asi = ultimatix_asi;
    }

    public int getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(int asignacion) {
        this.asignacion = asignacion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Date getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(Date fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
}