
package com.c2b.jornadas.ws;

import com.c2b.jornadas.excepciones.RegistroException;
import com.c2b.jornadas.modelo.Registro;
import com.c2b.jornadas.servicios.GestionRegistroService;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("registros")
public class RegistrosRestFulWS {

    @EJB
    private GestionRegistroService servicio;
    
    
    @GET()
    @Path("empleado/{idEmpleado}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Registro> find(@PathParam("idEmpleado") Integer id) throws RegistroException{
        List<Registro> resultado = (List<Registro>) servicio.getAllRegistrosEmpleado(id);
        return resultado;
    }
    
    
    @GET
    @Path("iniciarCheckIn/empleado/{idEmpleado}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void iniciarCheckIn(@PathParam("idEmpleado") Integer id) throws RegistroException{
        servicio.iniciarJornada(id);
    }
    
    
    
}
