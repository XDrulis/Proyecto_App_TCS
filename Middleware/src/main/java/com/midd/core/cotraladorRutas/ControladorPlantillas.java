package com.midd.core.cotraladorRutas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.midd.core.RecursosAsociados;
import com.midd.core.modelo.Asociado;

@Controller
@RequestMapping("/")
public class ControladorPlantillas {
	private final RecursosAsociados recursosAsociados;
	private final Asociado asociado;
	
	public ControladorPlantillas(RecursosAsociados recursosAsociados) {
		super();
		this.recursosAsociados = recursosAsociados;
		this.asociado = new Asociado();
	}

	@GetMapping("login")
	public String loginView() {
		return"login";
	}
	
	@GetMapping("registro")
	public String registroView() {
		return"registro";
	}
	
	@GetMapping("index")
	public String indexView() {		
		return"index";
	}
	
	@GetMapping("success")
	public String successView(@PathVariable String id_numero_Ultimatix, 
			@PathVariable String clave,
			@PathVariable String nombre,
			@PathVariable String apellido,
			@PathVariable String telefono,
			@PathVariable String correo) {
		System.out.println(clave);
		//recursosAsociados.agregarAsociado1(asociado);
		return "success";
	}
}
