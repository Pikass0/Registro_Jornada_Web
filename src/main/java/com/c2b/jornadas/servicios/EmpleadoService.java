
package com.c2b.jornadas.servicios;

import com.c2b.jornadas.excepciones.EmpleadoException;
import com.c2b.jornadas.modelo.Empleado;
import java.util.Collection;
import javax.ejb.Local;
import javax.servlet.http.HttpSession;

@Local
public interface EmpleadoService {
    
    public  Empleado getEmpleado(int id) throws EmpleadoException;
    public void alta(Empleado empNuevo);
    public Collection<Empleado> getAllEmpleados();
    public Empleado getEmpleadoByDNI(String email) throws EmpleadoException;
    public void borrar(int id) throws EmpleadoException;
    public void modificar(Empleado empleado) throws EmpleadoException;
    public Empleado login(String dni, String clave, HttpSession sesion) throws EmpleadoException;
//    public void logout(Session sesion) throws EmpleadoException;
    
            
}
