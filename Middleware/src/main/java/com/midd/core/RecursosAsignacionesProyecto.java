package com.midd.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.midd.core.administracion.ServicioEquipo;
import com.midd.core.administracion.ServicosAsignacionProyecto;
import com.midd.core.modelo.AsignacionProyecto;
import com.midd.core.modelo.Equipo;
import com.midd.core.modelo.Perfil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMethod;
import com.midd.core.Respuestas.Respuestas;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/asignaciones-proyectos")
public class RecursosAsignacionesProyecto {
        private final ServicosAsignacionProyecto servicio_asignaciones;
        private final Respuestas respuestas;
        private final ServicioEquipo servicio_equipo;

        Logger logger = LoggerFactory.getLogger(RecursosAsignacionesProyecto.class);

        @Autowired
        public RecursosAsignacionesProyecto(ServicosAsignacionProyecto servicio_asignaciones, Respuestas respuestas,
                        ServicioEquipo servicio_equipo) {
                this.servicio_asignaciones = servicio_asignaciones;
                this.respuestas = respuestas;
                this.servicio_equipo = servicio_equipo;
        }

        @PostMapping("/agregar-asignacion-proyecto")
        public ResponseEntity<?> agregarAsignacionProyecto(@RequestBody AsignacionProyecto asignacion_proyecto) {
                if (servicio_asignaciones.validarMiembro(asignacion_proyecto.getUltimatix_asi(),
                                asignacion_proyecto.getId_equipo_asi())) {

                        logger.warn("Usuario " + asignacion_proyecto.getUltimatix_asi()
                                        + " ya se encuentra registrado en el proyecto "
                                        + asignacion_proyecto.getId_equipo_asi());
                        return new ResponseEntity<>(respuestas
                                        .respuestas("Usuario ya se encuentra resgistrado en este proyecto", "3000"),
                                        HttpStatus.BAD_REQUEST);
                }
                if (asignacion_proyecto.getFecha_inicio().after(asignacion_proyecto.getFecha_fin())) {
                        logger.warn("Fecha inicio no puede ser mayor que la fecha fin");
                        return new ResponseEntity<>(respuestas
                                        .respuestas("Fecha inicio no puede ser mayor que la fecha fin", "3000"),
                                        HttpStatus.BAD_REQUEST);
                }
                servicio_asignaciones.agregarMiembroEquipo(asignacion_proyecto.getUltimatix_asi(),
                                asignacion_proyecto.getId_equipo_asi(), asignacion_proyecto.getAsignacion());
                asignacion_proyecto.setEstado(true);
                servicio_asignaciones.agregarAsignacionProyecto(asignacion_proyecto);
                Map<String, Object> respuesta = new HashMap<>();
                Equipo buscado = servicio_equipo.buscarEquipoId(asignacion_proyecto.getId_equipo_asi());
                respuesta.put("id_asignacion_proyecto_asg", asignacion_proyecto.getId_asignacion_proyecto_asg());
                respuesta.put("nombre_equipo_asi", buscado.getNombre_equipo_asi());
                respuesta.put("ultimatix_asi", asignacion_proyecto.getUltimatix_asi());
                respuesta.put("asignacion", asignacion_proyecto.getAsignacion());
                respuesta.put("fecha_inicio", asignacion_proyecto.getFecha_inicio());
                respuesta.put("fecha_fin", asignacion_proyecto.getFecha_fin());
                respuesta.put("fecha_baja", asignacion_proyecto.getFecha_baja());
                respuesta.put("estado", asignacion_proyecto.getEstado());

                return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }

        @PostMapping("/actualizar-Fecha-fin")
        public ResponseEntity<?> actualizarFechaFinAsignacionProyecto(
                        @RequestBody AsignacionProyecto asignacion_proyecto) {
                try {
                        AsignacionProyecto mi = servicio_asignaciones.buscarAsigancionProyectoId(
                                        asignacion_proyecto.getId_asignacion_proyecto_asg());
                        if (mi.getFecha_inicio().after(asignacion_proyecto.getFecha_fin())) {
                                logger.warn("Fecha inicio no puede ser mayor que la fecha fin");
                                return new ResponseEntity<>(respuestas
                                                .respuestas("Fecha inicio no puede ser mayor que la fecha fin", "3000"),
                                                HttpStatus.BAD_REQUEST);
                        }
                        mi.setFecha_fin(asignacion_proyecto.getFecha_fin());
                        servicio_asignaciones.agregarAsignacionProyecto(mi);
                        return new ResponseEntity<>(mi, HttpStatus.OK);

                } catch (Exception e) {
                        logger.warn("La asignación " + asignacion_proyecto.getId_asignacion_proyecto_asg()
                                        + " no se encuentra registrada");
                        return new ResponseEntity<>(respuestas.respuestas("Asignación no registrada", "2021"),
                                        HttpStatus.BAD_REQUEST);
                }
        }

        @PostMapping("/dar-baja")
        public ResponseEntity<?> actualizarFechaDeBaja(
                        @RequestBody AsignacionProyecto asignacion_proyecto) {
                try {
                        AsignacionProyecto mi = servicio_asignaciones.buscarAsigancionProyectoId(
                                        asignacion_proyecto.getId_asignacion_proyecto_asg());
                        if (mi.getFecha_inicio().after(asignacion_proyecto.getFecha_baja())) {
                                logger.warn("Fecha inicio no puede ser mayor que la fecha fin");
                                return new ResponseEntity<>(respuestas.respuestas(
                                                "Fecha inicio no puede ser mayor que la fecha de baja", "3000"),
                                                HttpStatus.BAD_REQUEST);
                        }
                        mi.setFecha_baja(asignacion_proyecto.getFecha_baja());
                        mi.setEstado(false);
                        servicio_asignaciones.restarAsignacion(mi.getUltimatix_asi(),
                                        mi.getAsignacion());
                        servicio_asignaciones.quitarMiembroEquipo(mi.getUltimatix_asi(),
                                        mi.getId_equipo_asi());
                        servicio_asignaciones.agregarAsignacionProyecto(mi);
                        return new ResponseEntity<>(mi, HttpStatus.OK);

                } catch (Exception e) {
                        logger.warn("La asignación " + asignacion_proyecto.getId_asignacion_proyecto_asg()
                                        + " no se encuentra registrada");
                        return new ResponseEntity<>(respuestas.respuestas("Asignación no registrada", "2021"),
                                        HttpStatus.BAD_REQUEST);
                }
        }

        /*
         * @PostMapping("/obtener-asignaciones")
         * public ResponseEntity<?> obtenerAsignaciones(@RequestBody AsignacionProyecto
         * asignacion_proyecto) {
         * List<AsignacionProyecto> listaUltimatix = servicio_asignaciones
         * .buscarAsignacionUltimatix(asignacion_proyecto.getUltimatix_asi());
         * return new ResponseEntity<>(listaUltimatix, HttpStatus.OK);
         * }
         */

        @PostMapping("/obtener-asignaciones-ultimatix")
        public ResponseEntity<?> obtenerAsignacionesUltimatix(@RequestBody Perfil perfil) {

                List<Map<String, Object>> lista_respuestas = new ArrayList<>();
                for (AsignacionProyecto asg : servicio_asignaciones.buscarTodasAsignacionesProyecto()) {
                        if (asg.getUltimatix_asi().equals(perfil.getId_ultimatix())) {
                                Map<String, Object> respuesta = new HashMap<>();
                                Equipo buscado = servicio_equipo.buscarEquipoId(asg.getId_equipo_asi());
                                respuesta.put("id_asignacion_proyecto_asi", asg.getId_asignacion_proyecto_asg());
                                respuesta.put("id_equipo", asg.getId_equipo_asi());
                                respuesta.put("nombre_equipo_asi", buscado.getNombre_equipo_asi());
                                respuesta.put("tipo_equipo_asi", buscado.getTipo_equipo_asi());
                                respuesta.put("ultimatix_asi", asg.getUltimatix_asi());
                                respuesta.put("asignacion", asg.getAsignacion());
                                respuesta.put("fecha_inicio", asg.getFecha_inicio());
                                respuesta.put("fecha_fin", asg.getFecha_fin());
                                respuesta.put("fecha_baja", asg.getFecha_baja());
                                respuesta.put("estado", asg.getEstado());
                                lista_respuestas.add(respuesta);

                        }
                }

                return new ResponseEntity<>(lista_respuestas, HttpStatus.OK);
        }

        @PostMapping("/actualizar-asignacion")
        public ResponseEntity<?> actualizarAsignacion(@RequestBody AsignacionProyecto asignacion) {
                AsignacionProyecto asignacion_db = servicio_asignaciones
                                .buscarAsigancionProyectoId(asignacion.getId_asignacion_proyecto_asg());
                if (asignacion.getFecha_inicio() != null) {
                        if (asignacion.getFecha_fin() != null) {
                                if (asignacion.getFecha_inicio().after(asignacion.getFecha_fin())) {
                                        return new ResponseEntity<>(respuestas.respuestas(
                                                        "Fecha inicio no puede ser mayor que la fecha final", "3000"),
                                                        HttpStatus.BAD_REQUEST);
                                        
                                } else {
                                        asignacion_db.setFecha_inicio(asignacion.getFecha_inicio());
                                }
                        }else {
                                if (asignacion.getFecha_inicio().after(asignacion_db.getFecha_fin())) {
                                        return new ResponseEntity<>(respuestas.respuestas(
                                                        "Fecha inicio no puede ser mayor que la fecha final", "3000"),
                                                        HttpStatus.BAD_REQUEST);
                                        
                                } else {
                                        asignacion_db.setFecha_inicio(asignacion.getFecha_inicio());
                                }
                        }
                }
                if (asignacion.getFecha_fin() != null) {
                        asignacion_db.setFecha_fin(asignacion.getFecha_fin());
                }
                if (asignacion.getAsignacion() != 0) {
                        asignacion_db.setAsignacion(asignacion.getAsignacion());
                }

                return new ResponseEntity<>(asignacion_db, HttpStatus.OK);
        }

}
