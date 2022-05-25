package com.midd.core.administracion;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midd.core.Exepciones.ActivoNoEncontradoExcepcion;
import com.midd.core.Exepciones.AreaNoEncontradoExcepcion;
import com.midd.core.modelo.Activos;
import com.midd.core.modelo.Area;
import com.midd.core.modelo.Edificio;
import com.midd.core.modelo.Tipo;
import com.midd.core.repositorio.ActivosRepo;
import com.midd.core.repositorio.AreaRepo;
import com.midd.core.repositorio.EdificioRepo;
import com.midd.core.repositorio.TipoRepo;

@Service
public class ServiciosActivos {
	private final ActivosRepo activosRepo;
	private final AreaRepo areaRepo;
	private final EdificioRepo edificioRepo;
	private final TipoRepo tipoRepo;
	@Autowired
	public ServiciosActivos(ActivosRepo activosRepo, AreaRepo areaRepo, EdificioRepo edificioRepo, TipoRepo tipoRepo) {
		this.activosRepo = activosRepo;
		this.areaRepo = areaRepo;
		this.edificioRepo = edificioRepo;
		this.tipoRepo = tipoRepo;
	}
	//Activos
	public Activos agregarActivo(Activos activo) {
		return activosRepo.save(activo);
	}
	
	public boolean validarMAC(String mac) {
		List<Activos> activos = activosRepo.findAll();
		for (Activos iterante : activos) {
			if ((iterante.getDireccion_mac().equals(mac))&&(!iterante.isEstado()&&(!iterante.isBorrado_logico()))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean validarIP(String ip) {
		List<Activos> activos = activosRepo.findAll();
		for (Activos iterante : activos) {
			if ((iterante.getDireccion_ip().equals(ip))&&(!iterante.isEstado()&&(!iterante.isBorrado_logico()))) {
				return false;
			}
		}
		return true;
	}
	
	public Activos actualizarActivo(Activos activo) {
		return activosRepo.save(activo);
	}
	
	public void eliminarActivo(Long id) {
		activosRepo.deleteById(id);
	}
	
	public List<Activos> buscarTodos() {
		return activosRepo.findAll();
	}
	
	public Activos buscarPorId(Long id) {
		return activosRepo.findById(id)
				.orElseThrow(()->new ActivoNoEncontradoExcepcion("Activo con id "+id+" no encontrado"));
	}
	
	public List<Activos> buscarPorUltimatix(String ultimatix){
		List<Activos> activos = activosRepo.findAll();
		List<Activos> enviar = new ArrayList<>();
		for (Activos iterante : activos) {
			if (iterante.getId_ultimatix().equals(ultimatix)) {
				enviar.add(iterante);
			}
		}
		return enviar;
	}
	
	public boolean validarFecha(Date fecha1, Date fecha2) {
		if ((fecha1.before(fecha2))||(fecha1.equals(fecha2))) {
			return false;
		}
		return true;
	}
	//Area
	public List<Area> buscarAreas() {
		return areaRepo.findAll();
	}
	
	public Area agregarArea(Area nuevo) {
		List<Area> areas = areaRepo.findAll();
		for (Area iterante : areas) {
			if (iterante.getNombre().equals(nuevo.getNombre())) {
				throw new AreaNoEncontradoExcepcion("Area repetida");
			}
		}
		return areaRepo.save(nuevo);
	}
	
	//Edificio
	public List<Edificio> buscarEdificios() {
		return edificioRepo.findAll();
	}
	
	public Edificio agregarEdificio(Edificio nuevo) {
		return edificioRepo.save(nuevo);
	}
	
	//Tipo
	public List<Tipo> buscarTipos() {
		return tipoRepo.findAll();
	}
	
	public Tipo agregarTipo(Tipo nuevo) {
		return tipoRepo.save(nuevo);
	}
}
