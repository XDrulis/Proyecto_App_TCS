package com.midd.core.administracion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midd.core.Exepciones.UserNotFoundException;
import com.midd.core.modelo.Habilidades;
import com.midd.core.modelo.Perfil;
import com.midd.core.repositorio.HabilidadesRepo;
import com.midd.core.repositorio.PerfilRepo;

@Service
public class ServiciosPerfil {
	private HabilidadesRepo habilidadesRepo;
	private PerfilRepo perfilRepo;
	
	@Autowired
	public ServiciosPerfil(HabilidadesRepo habilidadesRepo, PerfilRepo perfilRepo) {
		this.habilidadesRepo = habilidadesRepo;
		this.perfilRepo = perfilRepo;
	}

	public ServiciosPerfil() {
		super();
	}
	
	//Perfil@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Activo no encontrado")
	
	public Perfil agregarPerfil(Perfil nuevo) {
		Perfil mio = this.perfilUltimatix(nuevo.getId_ultimatix());
		nuevo.setNombres_completos(mio.getNombres_completos());
		return perfilRepo.save(nuevo);
	}
	
	public Perfil perfilUltimatix(Long id) {
		return perfilRepo.findById(id).
				orElseThrow(()->new UserNotFoundException("Asociado con id "+id+" no encontrado"));
	}

	public List<Perfil> buscarTodos(){
		return perfilRepo.findAll();
	}
	
	public String[] habilidadesUltimatix(Long id) {
		Perfil mio = this.perfilUltimatix(id);
		String[] mios = mio.getHabilidades();
		return mios;
	}
	
	public boolean buscarPerfilId(Long id) {
		if (perfilRepo.findById(id).isEmpty()) {
			return true;
		}
		return false;		
	}
	
	public List<Perfil> buscarPerfiles(String habilidad){
		List<Perfil> mios = perfilRepo.findAll();
		List<Perfil> perfiles = new ArrayList<>();
		for (Perfil iterante : mios) {
			String[] mi = iterante.getHabilidades();
			try {
				if (!mi.equals(null)) {
					for(int i=0; i<mi.length;i++) {
						if (mi[i].equals(habilidad)) {
							perfiles.add(iterante);
						}
					}
				}
			} catch (Exception e) {
			}			
		}
		return perfiles;
	}
	
	public void actualizarAsignacion(Perfil asignacionActualizada){
		perfilRepo.save(asignacionActualizada);
	}

	public Perfil buscarPerfilMio(Long ultimatix){
		return this.perfilUltimatix(ultimatix);
	}

	//Habilidades Catalogo
	
	public Habilidades agregarHabilidades(Habilidades nuevo) {
		return habilidadesRepo.save(nuevo);
	}
	
	public List<Habilidades> buscarHabilidades(){
		return habilidadesRepo.findAll();
	}
	
	public Object habilidadesDuplicadas(Perfil perfil){
		Perfil mi_perfil = perfilRepo.getById(perfil.getId_ultimatix());
		for(String habilidad: perfil.getHabilidades()){
			for(String mis_habilidad: mi_perfil.getHabilidades()){
				if(habilidad.equals(mis_habilidad)){
					return false;
				}
			}
			
		}
		String[] mis_habilidades = mi_perfil.getHabilidades();
		String[] mis_niveles = mi_perfil.getNivel_habilidad();

		List<String> habilidades = new ArrayList<>(Arrays.asList(mis_habilidades));
		List<String> niveles_habilidad = new ArrayList<>(Arrays.asList(mis_niveles));
		
		for(int i = 0; i < perfil.getHabilidades().length; i++){
			habilidades.add(perfil.getHabilidades()[i]);
			niveles_habilidad.add(perfil.getNivel_habilidad()[i]);
		}

		String[] nuevas_habilidades = new String[habilidades.size()];
		String[] nuevos_niveles = new String [niveles_habilidad.size()];

		habilidades.toArray(nuevas_habilidades);
		niveles_habilidad.toArray(nuevos_niveles);

		mi_perfil.setHabilidades(nuevas_habilidades);
		mi_perfil.setNivel_habilidad(nuevos_niveles);
		return perfilRepo.save(mi_perfil);
	}

	public Object buscarHabilidadUltimatix(Perfil perfil){
		Perfil mi_perfil = perfilRepo.getById(perfil.getId_ultimatix());
		String[] mis_habilidades = mi_perfil.getHabilidades();
		String[] mis_niveles = mi_perfil.getNivel_habilidad();
		List<Object> habilidadesResponse = new ArrayList<>();
		List<String> habilidades = new ArrayList<>(Arrays.asList(mis_habilidades));
		List<String> niveles_habilidad = new ArrayList<>(Arrays.asList(mis_niveles));
		habilidadesResponse.add(habilidades);
		habilidadesResponse.add(niveles_habilidad);
		return habilidadesResponse;
	}

}
