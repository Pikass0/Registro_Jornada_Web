package com.c2b.jornadas.servicios;

import com.c2b.jornadas.excepciones.EmpleadoException;
import com.c2b.jornadas.modelo.Empleado;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

//Es una sesion bean local
@Stateless
public class EmpleadoServiceImpl implements EmpleadoService {
    
    @PersistenceContext(unitName = "JornadasPU")
    private EntityManager em;    
    
    @Override
    public Empleado getEmpleado(int id) throws EmpleadoException {
        Empleado e = em.find(Empleado.class, id);
        if (e == null) {
            throw new EmpleadoException("No existe el empleado solicitado");
        }
        return e;
    }
    
    @Override
    public void alta(Empleado empNuevo) throws EmpleadoException {
        try {
            em.persist(empNuevo);
        } catch (EJBException e) {
            throw new EmpleadoException("El empleado ya existe");
        }//todo no consigo atrapar la excepcion cuando alguien ya existe
        catch (Exception e) {
            throw new EmpleadoException("Error al intentar dar de alta. " + e.getMessage());
        }
        
    }
    
    @Override
    public void baja(List<Empleado> empleados, boolean activo) throws EmpleadoException {
        for (Empleado empleado : empleados) {
            empleado.setActivo(activo);
            em.merge(empleado);
            
        }
    }
    
    @Override
    public Collection<Empleado> getAllEmpleados() {
        Query query = em.createNamedQuery("Empleado.findAll");
        
        return query.getResultList();
    }
    
    @Override
    public Empleado getEmpleadoByDNI(String dni) throws EmpleadoException {
        Query query = em.createNamedQuery("Empleado.findByDni");
        query.setParameter("dni", dni);
        
        try {
            Empleado e = (Empleado) query.getSingleResult();
            return e;
            
        } catch (javax.persistence.NoResultException e) {
            throw new EmpleadoException("No existe un empleado con ese dni " + dni);
        }
    }
    
    @Override
    public void modificar(Empleado empleado) throws EmpleadoException {
        //hace find y asi se sincroniza con el em con el commit que hace internamente
        Empleado e = this.getEmpleado(empleado.getIdEmpleado());
        if (e == null) {
            throw new EmpleadoException("No se ha encontrado el empleado");
        }
        em.merge(empleado);

        //los metodos de un ejb hacen commit al final si no hay excepciones
        //COMMIT -> se sincronizan los objetos de em con las tablas de bd
    }
    
    @Override
    public void borrar(int i) throws EmpleadoException {
        Empleado e = this.getEmpleado(i);
        if (e == null) {
            throw new EmpleadoException("No se ha podido encontrar ese empleado");
        }
        em.remove(e);
    }
    
    @Override
    public Empleado login(String dni, String clave, HttpSession sesion) throws EmpleadoException {
        
        try {
            Empleado eEncontrado = getEmpleadoByDNI(dni);
            if (eEncontrado.getPassword().equals(clave) && eEncontrado.getAdministrador()) {
                sesion.setAttribute("usuario", eEncontrado);
                return eEncontrado;
            }
            //si no coincide la contra
            throw new EmpleadoException("La constraseña es incorrecta");
        } catch (EmpleadoException e) {
            //si no lo ha encontrado
            throw new EmpleadoException(e.getMessage());
        }
        
    }

//    @Override
//    public void logout(Sess) throws EmpleadoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public Collection<Empleado> buscarEmploadoPorCriterio(String nombre) throws EmpleadoException {
        Query query = em.createNamedQuery("Empleado.findByNombre2");
        if (nombre == null || nombre.isEmpty()) {
            nombre = "%";
        } else {
            nombre = nombre.toLowerCase();
        }
        
        query.setParameter("nombre", nombre);
        
        query.setMaxResults(10);
        
        List<Empleado> empleados = query.getResultList();
        
        if (empleados == null || empleados.isEmpty()) {
            throw new EmpleadoException("info_busqueda_no_resultados");
        }
        
        return empleados;
    }
    
}
