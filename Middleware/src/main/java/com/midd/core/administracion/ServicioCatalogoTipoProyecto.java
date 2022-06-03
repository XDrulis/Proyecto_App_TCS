package com.midd.core.administracion;
import com.midd.core.repositorio.TipoProyectoRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midd.core.modelo.TipoProyecto;
@Service
public class ServicioCatalogoTipoProyecto {
    private final TipoProyectoRepo tipoProyectoRepo;

    @Autowired
    public ServicioCatalogoTipoProyecto(TipoProyectoRepo tipoProyectoRepo) {
        this.tipoProyectoRepo = tipoProyectoRepo;
    }
    public List<TipoProyecto> obtenerTiposProyectos(){
        return this.tipoProyectoRepo.findAll();           
    }    
}