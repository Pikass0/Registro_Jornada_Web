package com.c2b.jornadas.servicios;


import com.c2b.jornadas.excepciones.EmpleadoException;
import com.c2b.jornadas.modelo.Empleado;
import java.util.Collection;
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
    public void alta(Empleado empNuevo) {
       em.persist(empNuevo);
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
        
        try{
            Empleado e = (Empleado) query.getSingleResult();
            return e;
            
        }catch(javax.persistence.NoResultException e){
            throw new EmpleadoException("No existe un empleado con ese dni "+ dni);
        }
    }

    @Override
    public void modificar(Empleado empleado) throws EmpleadoException {
         //hace find y asi se sincroniza con el em con el commit que hace internamente
        Empleado e = this.getEmpleado(empleado.getIdEmpleado());
        if(e==null){
            throw new EmpleadoException("No se ha encontrado el empleado");
        }
        if (!empleado.getNombre().isEmpty()) {
            e.setNombre(empleado.getNombre());

        }
        if(!empleado.getEmail().isEmpty()){
            e.setEmail(empleado.getEmail());

        }
        if(!empleado.getPassword().isEmpty()){
            e.setPassword(empleado.getPassword());

        }
        
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
        
        try{
            Empleado eEncontrado = getEmpleadoByDNI(dni);
            if (eEncontrado.getPassword().equals(clave) && eEncontrado.isAdministrador()) {
                sesion.setAttribute("usuario", eEncontrado);
                return eEncontrado;
            } 
            //si no coincide la contra
            throw new EmpleadoException("La constraseña es incorrecta");
        }catch(EmpleadoException e){
            //si no lo ha encontrado
             throw new EmpleadoException( e.getMessage());
        }

        
    }

//    @Override
//    public void logout(Sess) throws EmpleadoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
    
    
    
    
    
}
