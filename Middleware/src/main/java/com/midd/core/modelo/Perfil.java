package com.midd.core.modelo;



import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="perfil_asi")
public class Perfil implements Serializable{
	@Id
	@Column(name="id_ultimatix_perfil",nullable = false,updatable = false)
	private Long id_ultimatix;
	@Column(name="sobreMi_perfil",nullable=true)
	private String sobreMi;
	@Column(name="habilidades_perfil",nullable=true)
	private String[] habilidades;
	@Column(name = "nivel_habilidad_perfil")
	private String[] nivel_habilidad;
	@Column(name="usuario_red_perfil",nullable=true)
	private String usuario_red;
	@Column(name="asignacion_perfil",nullable=true)
	private int asignacion_usuario;
	@Column(name="nombres_perfil",nullable=true)
	private String nombres_completos;
	
	public Perfil() {
	}

	public Perfil(Long id_ultimatix, String sobreMi, String[] habilidades, String[] nivel_habilidad, String usuario_red,
			int asignacion_usuario, String nombres_completos) {
		this.id_ultimatix = id_ultimatix;
		this.sobreMi = sobreMi;
		this.habilidades = habilidades;
		this.nivel_habilidad = nivel_habilidad;
		this.usuario_red = usuario_red;
		this.asignacion_usuario = asignacion_usuario;
		this.nombres_completos = nombres_completos;
	}

	public Long getId_ultimatix() {
		return id_ultimatix;
	}

	public void setId_ultimatix(Long id_ultimatix) {
		this.id_ultimatix = id_ultimatix;
	}

	public String getSobreMi() {
		return sobreMi;
	}

	public void setSobreMi(String sobreMi) {
		this.sobreMi = sobreMi;
	}

	public String[] getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(String[] habilidades) {
		this.habilidades = habilidades;
	}

	public String[] getNivel_habilidad() {
		return nivel_habilidad;
	}

	public void setNivel_habilidad(String[] nivel_habilidad) {
		this.nivel_habilidad = nivel_habilidad;
	}

	public String getUsuario_red() {
		return usuario_red;
	}

	public void setUsuario_red(String usuario_red) {
		this.usuario_red = usuario_red;
	}

	public int getAsignacion_usuario() {
		return asignacion_usuario;
	}

	public void setAsignacion_usuario(int asignacion_usuario) {
		this.asignacion_usuario = asignacion_usuario;
	}

	public String getNombres_completos() {
		return nombres_completos;
	}

	public void setNombres_completos(String nombres_completos) {
		this.nombres_completos = nombres_completos;
	}
	
	
	
}
