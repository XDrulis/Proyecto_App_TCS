package com.midd.core.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empleados_ac")
public class Asociado implements Serializable{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_empleados",nullable=false,updatable=false)
	private Long 	id_numero_Ultimatix;
	
	@Column(name="contrasenha_empleados",nullable=false)
	private String 	clave;
	
	@Column(name="nombre_empleados",nullable=false)
	private String 	nombre;
	
	@Column(name="apellido_empleados",nullable=false)
	private String 	apellido;
	
	@Column(name="telefono_empleados",nullable=false)
	private String 	telefono;
	
	@Column(name="correo_empleados",nullable=false)
	private String 	correo;

	public Asociado() {
	}

	public Asociado(Long id_numero_Ultimatix, String clave, String nombre, String apellido, String telefono,
			String correo) {
		this.id_numero_Ultimatix = id_numero_Ultimatix;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.correo = correo;
	}

	public Long getId_numero_Ultimatix() {
		return id_numero_Ultimatix;
	}

	public void setId_numero_Ultimatix(Long id_numero_Ultimatix) {
		this.id_numero_Ultimatix = id_numero_Ultimatix;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
