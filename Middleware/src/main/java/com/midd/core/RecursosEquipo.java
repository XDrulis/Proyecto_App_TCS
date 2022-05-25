package com.midd.core;

import java.util.List;

import com.midd.core.Respuestas.Respuestas;
import com.midd.core.administracion.ServicioCatalogoTipoProyecto;
import com.midd.core.administracion.ServicioEquipo;
import com.midd.core.modelo.Equipo;
import com.midd.core.modelo.TipoProyecto;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/equipos")
public class RecursosEquipo {

    private final ServicioEquipo servicio_equipo;
    private final Respuestas respuestas;
    private final ServicioCatalogoTipoProyecto servicioCatalogoTipoProyecto;
    public RecursosEquipo(ServicioEquipo servicio_equipo, Respuestas respuestas, ServicioCatalogoTipoProyecto servicioCatalogoTipoProyecto) {
        this.servicio_equipo = servicio_equipo;
        this.respuestas = respuestas;
        this.servicioCatalogoTipoProyecto = servicioCatalogoTipoProyecto;
    }

    @PostMapping("/agregar-equipo")
    public ResponseEntity<?> agregarEquipo(@RequestBody Equipo equipo) {

        if (!servicio_equipo.buscarEquipos(equipo)) {
            servicio_equipo.agregarEquipo(equipo);
            return new ResponseEntity<>(equipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuestas.respuestas("Equipo ya registrado", "3001"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/editar-equipo")
    public ResponseEntity<?> editarEquipo(@RequestBody Equipo equipo) {

        try {
            Equipo equipos = servicio_equipo.buscarEquipoId(equipo.getId_asi());
            servicio_equipo.actualizarEquipo(equipo);
            equipos.setNombre_lider(equipo.getNombre_lider());
            equipos.setNombre_tecnico(equipo.getNombre_tecnico());
            return new ResponseEntity<>(equipos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(respuestas.respuestas("No has editado el registro", "3001"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/eliminar-equipo")
    public ResponseEntity<?> eliminarEquipo(@RequestBody Equipo equipo) {
        try {
            Equipo equipos = servicio_equipo.buscarEquipoId(equipo.getId_asi());
            servicio_equipo.eliminarEquipo(equipos);
            return new ResponseEntity<>(equipos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(respuestas.respuestas("El equipo no existe", "3001"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar-equipos")
    public ResponseEntity<List<Equipo>> buscarEquipos() {
        List<Equipo> equipos = servicio_equipo.buscarTodosEquipos();
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @PostMapping("/buscar-equipo")
    public ResponseEntity<?> buscarEquiposId(@RequestBody Equipo equipo) {
        try {
            Equipo equipos = servicio_equipo.buscarEquipoId(equipo.getId_asi());
            return new ResponseEntity<>(equipos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(respuestas.respuestas("El equipo no existe", "3001"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/buscar-nombre-equipo")
    public ResponseEntity<?> buscarEquipoNombre(@RequestBody Equipo equipo) {

        List<Equipo> equipos = servicio_equipo.buscarNombreEquipo(equipo.getNombre_equipo_asi());
        if (equipos.isEmpty()) {
            return new ResponseEntity<>(respuestas.respuestas("Equipo no exixte", "400"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @PostMapping("/buscar-tipo-proyecto")
    public ResponseEntity<?> buscarTipoProyecto(@RequestBody Equipo equipo) {

        List<Equipo> equipos = servicio_equipo.listaEquipo(equipo.getTipo_equipo_asi());
        if (equipos.isEmpty()) {
            return new ResponseEntity<>(respuestas.respuestas("Ningún equipo coincide con la asignación ingresad", "3001"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @GetMapping("/obtener-tipo-equipos")
    public ResponseEntity<List<TipoProyecto>> obtenerTIpoEquipos() {
        return new ResponseEntity<>(this.servicioCatalogoTipoProyecto.obtenerTiposProyectos(), HttpStatus.OK);
    }
    


    
}
