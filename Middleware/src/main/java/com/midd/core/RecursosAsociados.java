package com.midd.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.midd.core.Respuestas.Respuestas;
import com.midd.core.administracion.ServiciosAsociados;
import com.midd.core.modelo.Asociado;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/asociados")
public class RecursosAsociados {
	private final ServiciosAsociados serviciosAsociados;
	private final PasswordEncoder cifrarClave;
	Logger logger = LoggerFactory.getLogger(RecursosActivos.class);
	private final Respuestas respuestas;
	//Constructor
	public RecursosAsociados(ServiciosAsociados serviciosAsociados, PasswordEncoder cifrarClave, Respuestas respuestas) {
		super();
		this.serviciosAsociados = serviciosAsociados;
		this.cifrarClave = cifrarClave;
		this.respuestas = respuestas;
	}
	
	@GetMapping("/buscarAsociados")
	public ResponseEntity<List<Asociado>> obtenerTodosAsociados(){
		List<Asociado> asociados = serviciosAsociados.buscarTodo();
		return new ResponseEntity<>(asociados,HttpStatus.OK);
	}	

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Asociado> obtenerAsociadoPorId(@PathVariable("id") Long id){
		Asociado asociado = serviciosAsociados.buscarAsociadoPorId(id);
		asociado.setClave("");
		return new ResponseEntity<>(asociado,HttpStatus.OK);
	}
	
	@PostMapping("/agregarAsociado")
	public ResponseEntity<?> agregarAsociado(@RequestBody Asociado asociado){
		try {
			serviciosAsociados.buscarAsociadoPorId(asociado.getId_numero_Ultimatix());
			logger.warn("El usuario "+ asociado.getId_numero_Ultimatix() +" ya se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Usuario ya registrado","1001"),HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			asociado.setClave(cifrarClave.encode(asociado.getClave()));
			if(serviciosAsociados.validarCorreo(asociado)==false) {
				logger.warn("El correo "+ asociado.getCorreo() +" ya se encuentra registrado");
				return new ResponseEntity<>(respuestas.respuestas("Correo ya registrado","1002"),HttpStatus.BAD_REQUEST);
			}		
			
			if(serviciosAsociados.validarTelefono(asociado)==false) {
				logger.warn("El telefono "+ asociado.getTelefono() +" ya se encuentra registrado");
				return new ResponseEntity<>(respuestas.respuestas("Telefono ya registrado","1003"),HttpStatus.BAD_REQUEST);
			}
			serviciosAsociados.agregarAsociado(asociado);
			asociado.setClave(null);
			logger.info("El asociado "+ asociado.getId_numero_Ultimatix() +" se ha registrado exitosamente");
			return new ResponseEntity<>(asociado,HttpStatus.OK);
		}				
	}
			
	@PostMapping("/actualizarAsociado")
	public ResponseEntity<?> actualizarAsociado(@RequestBody Asociado asociado){
		if (serviciosAsociados.buscarAsociadoId(asociado.getId_numero_Ultimatix())) {
			logger.warn("El asociado "+ asociado.getId_numero_Ultimatix() +" no se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Usuario no registrado","1021"),HttpStatus.BAD_REQUEST);
		}
		if(serviciosAsociados.validarCorreoActualizar(asociado)==false) {
			logger.warn("El correo "+ asociado.getCorreo() +" ya se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Correo ya registrado","1021"),HttpStatus.BAD_REQUEST);
		}		
		if(serviciosAsociados.validarTelefonoActualizar(asociado)==false) {
			logger.warn("El telefono "+ asociado.getTelefono() +" ya se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Telefono ya registrado","1023"),HttpStatus.BAD_REQUEST);
		}
		Asociado mio = serviciosAsociados.buscarAsociadoPorId(asociado.getId_numero_Ultimatix());
		mio.setCorreo(asociado.getCorreo());
		mio.setTelefono(asociado.getTelefono());
		Asociado actualizadoAsociado = serviciosAsociados.actualizarAsociado(mio);
		actualizadoAsociado.setClave("");
		return new ResponseEntity<>(actualizadoAsociado,HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Asociado> eliminarAsociadoPorId(@PathVariable("id") Long id){
		serviciosAsociados.eliminarAsociado(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/tcs-login")
	public ResponseEntity<?> iniciarSesion(@RequestBody Asociado asociado){
		Asociado nuevo = null;
		try {
			nuevo = serviciosAsociados.buscarAsociadoPorId(asociado.getId_numero_Ultimatix());
		} catch (Exception e) {
			logger.warn("El asociado "+ asociado.getId_numero_Ultimatix() +" no se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Usuario no registrado","1011"),HttpStatus.BAD_REQUEST);
		}				
		if (cifrarClave.matches(asociado.getClave(), nuevo.getClave())==false) {
			logger.warn("La contraseña ingresada de "+ asociado.getId_numero_Ultimatix() +" es incorrecta");
			return new ResponseEntity<>(respuestas.respuestas("Contraseña incorrecta","1012"),HttpStatus.BAD_REQUEST);
		}
		logger.info("El asociado "+ asociado.getId_numero_Ultimatix() +" ha igresado exitosamente");
		nuevo.setClave(null);
		return new ResponseEntity<>(nuevo,HttpStatus.OK);		
	}	
}