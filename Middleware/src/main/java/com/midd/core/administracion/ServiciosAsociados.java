package com.midd.core.administracion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midd.core.Exepciones.UserNotFoundException;
import com.midd.core.modelo.Asociado;
import com.midd.core.modelo.Perfil;
import com.midd.core.repositorio.AsociadosRepo;
import com.midd.core.repositorio.PerfilRepo;



@Service
public class ServiciosAsociados {
	private final AsociadosRepo asociadosRepo;
	private final PerfilRepo perfilRepo;
	
	@Autowired
	public ServiciosAsociados(AsociadosRepo asociadosRepo, PerfilRepo perfilRepo) {
		this.asociadosRepo = asociadosRepo;
		this.perfilRepo = perfilRepo;
	}
	
	public Asociado agregarAsociado(Asociado asociado) {
		String[] qwe = new String[0];
		perfilRepo.save(new Perfil(asociado.getId_numero_Ultimatix(),"",qwe,qwe,"",0,asociado.getNombre() + " " + asociado.getApellido()));
		return asociadosRepo.save(asociado);
	}

	public boolean validarCorreo(Asociado asociado) {
		List<Asociado> nuevo = asociadosRepo.findAll();
		for (Asociado iterante : nuevo) {
			if (iterante.getCorreo().equals(asociado.getCorreo())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean validarCorreoActualizar(Asociado asociado) {
		List<Asociado> nuevo = asociadosRepo.findAll();
		for (Asociado iterante : nuevo) {
			if ((iterante.getCorreo().equals(asociado.getCorreo()))&&(!iterante.getId_numero_Ultimatix().equals(asociado.getId_numero_Ultimatix()))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean validarTelefono(Asociado asociado) {
		List<Asociado> nuevo = asociadosRepo.findAll();
		for (Asociado iterante : nuevo) {
			if (iterante.getTelefono().equals(asociado.getTelefono())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean validarTelefonoActualizar(Asociado asociado) {
		List<Asociado> nuevo = asociadosRepo.findAll();
		for (Asociado iterante : nuevo) {
			if ((iterante.getTelefono().equals(asociado.getTelefono()))&&(!iterante.getId_numero_Ultimatix().equals(asociado.getId_numero_Ultimatix()))) {
				return false;
			}
		}
		return true;
	}
	
	public List<Asociado> buscarTodo(){
		return asociadosRepo.findAll();
	}
	
	
	public Asociado actualizarAsociado(Asociado asociadoOld) {
		return asociadosRepo.save(asociadoOld);
	}
	
	public Asociado buscarAsociadoPorId(Long Id) {
		return asociadosRepo.findById(Id)
				.orElseThrow(()->new UserNotFoundException("Asociado con id "+Id+" no encontrado"));
	}
	
	public boolean buscarAsociadoId(Long Id) {
		if (asociadosRepo.findById(Id).isEmpty()) {
			return true;
		}
		return false;
	}
	
	public void eliminarAsociado(Long Id) {
		if (asociadosRepo.existsById(Id)==false) {
			throw new UserNotFoundException("Asociado con id "+Id+" no encontrado");
		}
		asociadosRepo.deleteById(Id);
	}
}
