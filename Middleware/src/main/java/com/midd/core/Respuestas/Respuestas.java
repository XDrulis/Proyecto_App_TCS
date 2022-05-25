package com.midd.core.Respuestas;

import java.time.*;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class Respuestas {

    public Map<String, Object> respuestas(String mensaje, String codigo) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", mensaje);
        respuesta.put("status", codigo);
        String fechayhora = LocalDate.now(ZoneId.of("GMT-05:00")).toString() + " "
                + LocalTime.now(ZoneId.of("GMT-05:00")).toString();
        respuesta.put("timestamp", fechayhora);

        return respuesta;
    }

}
