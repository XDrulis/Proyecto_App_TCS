package com.midd.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.midd.core.Respuestas.Respuestas;
import com.midd.core.administracion.ServiciosPerfil;
import com.midd.core.modelo.Habilidades;
import com.midd.core.modelo.Perfil;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/perfil/")
public class RecursosPerfil {
	
	private final ServiciosPerfil serviciosPerfil;
	private final Respuestas respuestas;
	Logger logger = LoggerFactory.getLogger(RecursosActivos.class);
	
	@Autowired
	public RecursosPerfil(ServiciosPerfil serviciosPerfil, Respuestas respuestas) {
		this.serviciosPerfil = serviciosPerfil;
		this.respuestas = respuestas;
	}
	
	@PostMapping("actualizarPerfil")
	public ResponseEntity<?> agregarActivo(@RequestBody Perfil nuevo){
		if (serviciosPerfil.buscarPerfilId(nuevo.getId_ultimatix())) {
			logger.warn("El asociado "+ nuevo.getId_ultimatix() +" no se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Usuario no registrado", "2031"),HttpStatus.BAD_REQUEST);
		}
		logger.info("El asociado "+ nuevo.getId_ultimatix() +" actualizo el perfil");
		serviciosPerfil.agregarPerfil(nuevo);
		return new ResponseEntity<>(nuevo,HttpStatus.OK);
	}
	
	@PostMapping("habilidadUltimatix")
	public ResponseEntity<?> habilidadUltimatix(@RequestBody Perfil nuevo){
		if (serviciosPerfil.buscarPerfilId(nuevo.getId_ultimatix())) {
			logger.warn("El asociado "+ nuevo.getId_ultimatix() +" no se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Usuario no registrado", "1011"),HttpStatus.BAD_REQUEST);
		}
		String[] mio = serviciosPerfil.habilidadesUltimatix(nuevo.getId_ultimatix());
		return new ResponseEntity<>(mio,HttpStatus.OK);
	}
	
	@PostMapping("ultimatixHabilidad")
	public ResponseEntity<?> ultimatixHabilidad(@RequestBody String habilidad){
		List<Perfil> mi = serviciosPerfil.buscarPerfiles(habilidad);
		return new ResponseEntity<>(mi,HttpStatus.OK);
	}
	
	@PostMapping("perfil")
	public ResponseEntity<?> perfil(@RequestBody Perfil nuevo){
		if (serviciosPerfil.buscarPerfilId(nuevo.getId_ultimatix())) {
			logger.warn("El asociado "+ nuevo.getId_ultimatix() +" no se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Usuario no registrado", "2031"),HttpStatus.BAD_REQUEST);
		}
		logger.info("El asociado "+ nuevo.getId_ultimatix() +" actualizo el perfil");
		Perfil mio = serviciosPerfil.perfilUltimatix(nuevo.getId_ultimatix());//Errores faltan colocar aun
		return new ResponseEntity<>(mio,HttpStatus.OK);
	}

	@GetMapping("perfiles")
	public ResponseEntity<?> perfilesTodo(){
		List<Perfil> mios = serviciosPerfil.buscarTodos();
		return new ResponseEntity<>(mios,HttpStatus.OK);
	}
	
	@PostMapping("sobreMi")
	public ResponseEntity<?> sobreMi(@RequestBody Perfil nuevo){
		Map<String, Object> response = new HashMap<>();
		if (serviciosPerfil.buscarPerfilId(nuevo.getId_ultimatix())) {
			logger.warn("El asociado "+ nuevo.getId_ultimatix() +" no se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Usuario no registrado", "2031"),HttpStatus.BAD_REQUEST);
		}
		Perfil mio = serviciosPerfil.perfilUltimatix(nuevo.getId_ultimatix());
		mio.setSobreMi(nuevo.getSobreMi());
		serviciosPerfil.agregarPerfil(mio);
		return new ResponseEntity<>(mio,HttpStatus.OK);
	}
	
	@PostMapping("habilidades")//agregar el nivel
	public ResponseEntity<?> habilidades(@RequestBody Perfil nuevo){
		Map<String, Object> response = new HashMap<>();
		if (serviciosPerfil.buscarPerfilId(nuevo.getId_ultimatix())) {
			logger.warn("El asociado "+ nuevo.getId_ultimatix() +" no se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Usuario no registrado", "2031"),HttpStatus.BAD_REQUEST);
		}
		Perfil mio = serviciosPerfil.perfilUltimatix(nuevo.getId_ultimatix());
		mio.setHabilidades(nuevo.getHabilidades());
		serviciosPerfil.agregarPerfil(mio);
		return new ResponseEntity<>(mio,HttpStatus.OK);
	}
	
	@PostMapping("usuarioRed")
	public ResponseEntity<?> usuarioRed(@RequestBody Perfil nuevo){
		Map<String, Object> response = new HashMap<>();
		if (serviciosPerfil.buscarPerfilId(nuevo.getId_ultimatix())) {
			logger.warn("El asociado "+ nuevo.getId_ultimatix() +" no se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Usuario no registrado", "2031"),HttpStatus.BAD_REQUEST);
		}
		Perfil mio = serviciosPerfil.perfilUltimatix(nuevo.getId_ultimatix());
		mio.setUsuario_red(nuevo.getUsuario_red());
		serviciosPerfil.agregarPerfil(mio);
		return new ResponseEntity<>(mio,HttpStatus.OK);
	}
	
	//Catalogo habilidades
	
	@GetMapping("habilidades")// añadir nivel
	public ResponseEntity<?> habilidades(){
		List<Habilidades> mis = serviciosPerfil.buscarHabilidades();
		return new ResponseEntity<>(mis,HttpStatus.OK);
	}
	
	@PostMapping("agregarHabilidad")
	public ResponseEntity<?> agregarHabilidad(@RequestBody Habilidades habilidad){
		List<Habilidades> mis = serviciosPerfil.buscarHabilidades();
		for (Habilidades iterante : mis) {
			if  (iterante.getNombre().equals(habilidad.getNombre())){
				return new ResponseEntity<>(respuestas.respuestas("Habilidad ya registrada", "400"),HttpStatus.BAD_REQUEST);
			}
		}
		serviciosPerfil.agregarHabilidades(habilidad);
		return new ResponseEntity<>(habilidad,HttpStatus.OK);
	}

	@PostMapping("mis-habilidades")
	public ResponseEntity<?> misHabilidades(@RequestBody Perfil perfil){
		Object perfil_habilidades = serviciosPerfil.habilidadesDuplicadas(perfil);
		if(perfil_habilidades.equals(false)){
			return new ResponseEntity<>(respuestas.respuestas("Habilidad ya registrada en este perfil", "3001"),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(perfil_habilidades,HttpStatus.OK);
	}
	
	// 
	// 	{nombre:AWS,nivel:medio},
	// 	{nombre:Azure,nivel:alto}
	// ]
}
