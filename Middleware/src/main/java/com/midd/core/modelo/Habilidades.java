package com.midd.core.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="habilidades_ac")
public class Habilidades implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_habilidad",nullable = false,updatable = false)
	private Long id;
	
	@Column(name="nombre_habilidad",nullable=false)
	private String 	nombre;

	public Habilidades(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Habilidades() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Habilidades [id=" + id + ", nombre=" + nombre + "]";
	}
}
