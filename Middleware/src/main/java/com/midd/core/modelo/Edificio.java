package com.midd.core.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="edificio_ac")
public class Edificio implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_edificio",nullable = false,updatable = false)
	private Long id;
	
	@Column(name="nombre_edificio",nullable=false)
	private String 	nombre;
	
	@Column(name="piso_edificio",nullable=false)
	private String	piso;
	
	//Constructor
	public Edificio(Long id, String nombre, String piso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.piso = piso;
	}
	//Getter and Setter
	public Edificio() {
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

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}
	//toString
	@Override
	public String toString() {
		return "Edificio [id=" + id + ", nombre=" + nombre + ", piso=" + piso + "]";
	}
		
}
