/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c2b.jornadas.servicios;

import com.c2b.jornadas.excepciones.RegistroException;
import com.c2b.jornadas.modelo.Registro;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Stateless

public class GestionRegistroServiceImpl implements GestionRegistroService {

    @PersistenceContext(unitName = "JornadasPU")
    private EntityManager em;
    
    @Override
    public void iniciarJornada(Integer idEmpleado) throws RegistroException {
        //validar el id null
        if (idEmpleado == null) {
            throw new RegistroException("Debe indicar el id de Empleado");
        }
        //ver si no esta ya la jornada iniciada hoy y hora fin es null
        Query query = em.createNamedQuery("Registro.findJornadasNoFinalizadasEmpleado");
        query.setParameter("idEmpleado", idEmpleado);
//        query.setParameter("dia", LocalDate.now());
        Date hoy = new Date();
        //de la fecha de hoy, solo queremos DATE
        query.setParameter("dia", hoy, TemporalType.DATE);
  
        //si hay jornada iniciada, enviar exception
        List<Registro> resultado = query.getResultList();
        if (resultado.size() > 0) {
            throw new RegistroException("La jornada ya está iniciada");
        }
        //si no, insert registro fecha hoy y null hora fin
        Registro checkIn = new Registro(idEmpleado);
        checkIn.setDia(hoy);//guarda fecha
        checkIn.setCheckIn(hoy);//guarda hora
        checkIn.setCheckOut(null);
        
        em.persist(checkIn);
        
        
        
    }

    @Override
    public Collection<Registro> getAllRegistrosEmpleado(Integer idEmpleado) throws RegistroException {     
        //validar el id null
        if (idEmpleado == null) {
            throw new RegistroException("Debe indicar el id de Empleado");
        }
        Query query = em.createNamedQuery("Registro.findByIdEmpleado");
        query.setParameter("idEmpleado", idEmpleado);
        return query.getResultList();
    }

}
