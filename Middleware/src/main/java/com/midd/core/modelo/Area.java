package com.midd.core.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="area_ac")
public class Area implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_area",nullable = false,updatable = false)
	private Long id;
	
	@Column(name="nombre_area",nullable=false)
	private String 	nombre;
	//Constructor
	public Area(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public Area() {
		super();
	}
	//getters and setters
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
	//toString
	@Override
	public String toString() {
		return "Area [id=" + id + ", nombre=" + nombre + "]";
	}
}
