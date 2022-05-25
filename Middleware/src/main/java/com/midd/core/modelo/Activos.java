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
@Table(name="activos_ac")
public class Activos implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_ac",nullable = false,updatable = false)
	private Long id_activo;
	
	@Column(name="area_ac",nullable=false)
	private String 	area;
	
	@Column(name="edificio_ac",nullable=false)
	private String 	edificio;
	
	@Column(name="piso_ac",nullable=false)
	private String 	piso;
	
	@Column(name="tipo_ac",nullable=false)
	private String 	tipo;
	
	@Column(name="hostname_ac",nullable=false)
	private String 	hostname;
	
	@Column(name="direccion_mac_ac",nullable=false)
	private String 	direccion_mac;
	
	@Column(name="direccion_ip_ac",nullable=false)
	private String 	direccion_ip;
	
	@Column(name="ip_reservada_ac",nullable=false)
	private boolean	reservada_ip;

	@Column(name="marca_ac", nullable = false)
	private String marca;

	@Column(name="modelo_ac", nullable = false)
	private String modelo;

	@Column(name="serie_ac", nullable = false)
	private String serie;
	
	@Column(name="codigo_barras_ac", nullable = false)
	private String codigo_barras;

	@Column(name="borrado_logico_ac",nullable=false)
	private boolean	borrado_logico;
	
	@Column(name="estado_ac",nullable=false)
	private boolean	estado;
	
	@Column(name="fecha_registro_ac",nullable=false)
	private Date fecha_registro;
	
	@Column(name="fecha_entrega_ac",nullable=true)
	private Date fecha_entrega;
	
	@Column(name="fecha_devolucion_ac",nullable=true)
	private Date	fecha_devolucion;
	
	@Column(name="usuario_ac",nullable=false)
	private String 	id_ultimatix;
	
	public Activos(){
		
	}

	public Activos(Long id_activo, String area, String edificio, String piso, String tipo, String hostname,
			String direccion_mac, String direccion_ip, boolean reservada_ip, String marca, String modelo, String serie,
			String codigo_barras, boolean borrado_logico, boolean estado, Date fecha_registro, Date fecha_entrega,
			Date fecha_devolucion, String id_ultimatix) {
		this.id_activo = id_activo;
		this.area = area;
		this.edificio = edificio;
		this.piso = piso;
		this.tipo = tipo;
		this.hostname = hostname;
		this.direccion_mac = direccion_mac;
		this.direccion_ip = direccion_ip;
		this.reservada_ip = reservada_ip;
		this.marca = marca;
		this.modelo = modelo;
		this.serie = serie;
		this.codigo_barras = codigo_barras;
		this.borrado_logico = borrado_logico;
		this.estado = estado;
		this.fecha_registro = fecha_registro;
		this.fecha_entrega = fecha_entrega;
		this.fecha_devolucion = fecha_devolucion;
		this.id_ultimatix = id_ultimatix;
	}

	public Long getId_activo() {
		return id_activo;
	}

	public void setId_activo(Long id_activo) {
		this.id_activo = id_activo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getDireccion_mac() {
		return direccion_mac;
	}

	public void setDireccion_mac(String direccion_mac) {
		this.direccion_mac = direccion_mac;
	}

	public String getDireccion_ip() {
		return direccion_ip;
	}

	public void setDireccion_ip(String direccion_ip) {
		this.direccion_ip = direccion_ip;
	}

	public boolean isReservada_ip() {
		return reservada_ip;
	}

	public void setReservada_ip(boolean reservada_ip) {
		this.reservada_ip = reservada_ip;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getCodigo_barras() {
		return codigo_barras;
	}

	public void setCodigo_barras(String codigo_barras) {
		this.codigo_barras = codigo_barras;
	}

	public boolean isBorrado_logico() {
		return borrado_logico;
	}

	public void setBorrado_logico(boolean borrado_logico) {
		this.borrado_logico = borrado_logico;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Date getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public Date getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(Date fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public String getId_ultimatix() {
		return id_ultimatix;
	}

	public void setId_ultimatix(String id_ultimatix) {
		this.id_ultimatix = id_ultimatix;
	}

	
	
}
