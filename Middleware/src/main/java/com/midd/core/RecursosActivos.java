package com.midd.core;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.midd.core.administracion.ServiciosActivos;
import com.midd.core.modelo.Activos;
import com.midd.core.modelo.Area;
import com.midd.core.modelo.Edificio;
import com.midd.core.modelo.Tipo;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/activos")
public class RecursosActivos {
	private final ServiciosActivos serviciosActivos;
	private final Respuestas respuestas;
	Logger logger = LoggerFactory.getLogger(RecursosActivos.class);

	public RecursosActivos(ServiciosActivos serviciosActivos, Respuestas respuestas) {
		this.serviciosActivos = serviciosActivos;
		this.respuestas = respuestas;
	}

	@PostMapping("/agregarActivo")
	public ResponseEntity<?> agregarActivo(@RequestBody Activos activo) {
		try {
			serviciosActivos.buscarPorId(activo.getId_activo());
			logger.warn("El activo " + activo.getId_activo() + " ya se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Activo ya registrado", "2001"), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			if(activo.getTipo().equals("CPU/Portatil")){
				System.out.println("Direccion mac capturada --> " + activo.getDireccion_mac());
				if (!serviciosActivos.validarMAC_repetida(activo.getDireccion_mac())) {
					logger.warn("La MAC " + activo.getDireccion_mac() + " ya se encuentra registrada");
					return new ResponseEntity<>(respuestas.respuestas("MAC ya registrada", "2002"), HttpStatus.BAD_REQUEST);
				}
				if (!serviciosActivos.isValidMacAddress(activo.getDireccion_mac())) {
					logger.warn("La MAC " + activo.getDireccion_mac() + " ya se encuentra registrada");
					return new ResponseEntity<>(respuestas.respuestas("MAC no válida", "2004"), HttpStatus.BAD_REQUEST);
				}
				
				if (!serviciosActivos.validarIPrepetida(activo.getDireccion_ip())) {
					logger.warn("La IP " + activo.getDireccion_ip() + " ya se encuentra registrada");
					return new ResponseEntity<>(respuestas.respuestas("IP ya registrada", "2003"), HttpStatus.BAD_REQUEST);
				}
				if (!serviciosActivos.isValidIPAddress(activo.getDireccion_ip())) {
					
					return new ResponseEntity<>(respuestas.respuestas("IP no válida", "2005"), HttpStatus.BAD_REQUEST);
				}
			}
			activo.setBorrado_logico(false);
			activo.setEstado(false);
			System.out.println(activo.getFecha_entrega());
			activo.setFecha_registro(Date.valueOf(LocalDate.now(ZoneId.of("GMT-05:00"))));
			LocalDate uno = activo.getFecha_entrega().toLocalDate();
			activo.setFecha_entrega(Date.valueOf(uno));
			if (serviciosActivos.validarFecha(activo.getFecha_entrega(), activo.getFecha_registro())) {
				logger.warn("La fecha de entrega " + activo.getFecha_entrega()
						+ " no puede ser mayor a la fecha de registro " + activo.getFecha_registro());
				return new ResponseEntity<>(
						respuestas.respuestas("La fecha de entrega no puede ser mayor a la fecha de registro", "2004"),
						HttpStatus.BAD_REQUEST);
			}
			serviciosActivos.agregarActivo(activo);
			logger.info("El activo " + activo + " se registró exitosamente");
			List<Activos> activos = serviciosActivos.buscarPorUltimatix(activo.getId_ultimatix());
			return new ResponseEntity<>(activos, HttpStatus.OK);
		}
	}

	@GetMapping("/buscarTodo")
	public ResponseEntity<?> buscarTodo() {
		List<Activos> activos = serviciosActivos.buscarTodos();
		return new ResponseEntity<>(activos, HttpStatus.OK);
	}

	@PostMapping("/buscarUltimatix")
	public ResponseEntity<?> buscarActivosPorUltimatix(@RequestBody Activos activo) {
		List<Activos> activos = serviciosActivos.buscarPorUltimatix(activo.getId_ultimatix());
		return new ResponseEntity<>(activos, HttpStatus.OK);
	}

	@PostMapping("/eliminarActivo")
	public ResponseEntity<?> eliminarActivo(@RequestBody Activos activo) {
		try {
			Activos activo1 = serviciosActivos.buscarPorId(activo.getId_activo());
			if (activo1.getId_ultimatix().equals(activo.getId_ultimatix())) {
				activo1.setBorrado_logico(true);
				serviciosActivos.actualizarActivo(activo1);
				logger.info("El activo " + activo + " se eliminó exitosamente (borrado logico)");
				List<Activos> activos = serviciosActivos.buscarPorUltimatix(activo.getId_ultimatix());
				return new ResponseEntity<>(activos, HttpStatus.OK);
			}
			logger.warn("El activo " + activo.getId_activo() + " no puede ser eliminado por el usuario "
					+ activo.getId_ultimatix() + "!");
			return new ResponseEntity<>(respuestas.respuestas("Usuario no puede eliminar activo", "2022"),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			logger.warn("El activo " + activo.getId_activo() + " no se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Activo no registrado", "2021"), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("devolverActivo")
	public ResponseEntity<?> devolverActivo(@RequestBody Activos activo) {
		try {
			Activos activo1 = serviciosActivos.buscarPorId(activo.getId_activo());
			if (activo1.getId_ultimatix().equals(activo.getId_ultimatix())) {
				activo1.setEstado(true);
				activo1.setFecha_devolucion(activo.getFecha_devolucion());
				LocalDate uno = activo1.getFecha_devolucion().toLocalDate();
				activo1.setFecha_devolucion(Date.valueOf(uno));
				if (serviciosActivos.validarFecha(activo1.getFecha_registro(), activo1.getFecha_devolucion())) {
					logger.warn("La fecha de devolucion " + activo1.getFecha_devolucion()
							+ " no puede ser menor a la fecha de registro " + activo1.getFecha_registro());
					return new ResponseEntity<>(respuestas
							.respuestas("La fecha de devolucion no puede ser menor a la fecha de registro", "2013"),
							HttpStatus.BAD_REQUEST);
				}
				serviciosActivos.actualizarActivo(activo1);
				logger.info("El activo " + activo1 + " se devolvió exitosamente ()");
				List<Activos> activos = serviciosActivos.buscarPorUltimatix(activo.getId_ultimatix());
				return new ResponseEntity<>(activos, HttpStatus.OK);
			}
			logger.warn("El activo " + activo.getId_activo() + " no puede ser eliminado por el usuario "
					+ activo.getId_ultimatix() + "!");
			return new ResponseEntity<>(respuestas.respuestas("Usuario no puede devolver activo", "2012"),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			logger.warn("El activo " + activo.getId_activo() + " no se encuentra registrado");
			return new ResponseEntity<>(respuestas.respuestas("Activo no registrado", "2011"), HttpStatus.BAD_REQUEST);
		}
	}

	// Edificios
	@GetMapping("/edificios")
	public ResponseEntity<?> buscarEdificios() {
		List<Edificio> edificios = serviciosActivos.buscarEdificios();
		return new ResponseEntity<>(edificios, HttpStatus.OK);
	}

	@PostMapping("/agregarEdificio")
	public ResponseEntity<?> agregarEdificio(@RequestBody Edificio nuevo) {
		List<Edificio> edificios = serviciosActivos.buscarEdificios();
		for (Edificio iterante : edificios) {
			if (iterante.getNombre().equals(nuevo.getNombre())) {
				return new ResponseEntity<>(respuestas.respuestas("Edificio ya registrado", "400"),
						HttpStatus.BAD_REQUEST);
			}
		}
		serviciosActivos.agregarEdificio(nuevo);
		return new ResponseEntity<>(nuevo, HttpStatus.OK);
	}

	// Areas
	@GetMapping("/areas")
	public ResponseEntity<?> buscarAreas() {
		List<Area> areas = serviciosActivos.buscarAreas();
		return new ResponseEntity<>(areas, HttpStatus.OK);
	}

	@PostMapping("/agregarArea")
	public ResponseEntity<?> agregarEdificio(@RequestBody Area nuevo) {
		List<Area> areas = serviciosActivos.buscarAreas();
		for (Area iterante : areas) {
			if (iterante.getNombre().equals(nuevo.getNombre())) {
				return new ResponseEntity<>(respuestas.respuestas("Área ya registrado", "400"), HttpStatus.BAD_REQUEST);
			}
		}
		serviciosActivos.agregarArea(nuevo);
		return new ResponseEntity<>(nuevo, HttpStatus.OK);
	}

	// Tipos
	@GetMapping("/tipos")
	public ResponseEntity<?> buscarTipos() {
		List<Tipo> tipos = serviciosActivos.buscarTipos();
		return new ResponseEntity<>(tipos, HttpStatus.OK);
	}

	@PostMapping("/agregarTipo")
	public ResponseEntity<?> agregarTipos(@RequestBody Tipo nuevo) {
		List<Tipo> tipos = serviciosActivos.buscarTipos();
		for (Tipo iterante : tipos) {
			if (iterante.getNombre().equals(nuevo.getNombre())) {
				return new ResponseEntity<>(respuestas.respuestas("Tipo ya registrado", "400"), HttpStatus.BAD_REQUEST);
			}
		}
		serviciosActivos.agregarTipo(nuevo);
		return new ResponseEntity<>(nuevo, HttpStatus.OK);
	}
}