/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c2b.jornadas.servicios;

import com.c2b.jornadas.excepciones.RegistroException;
import com.c2b.jornadas.modelo.Registro;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface GestionRegistroService {
    public void iniciarJornada(Integer idEmpleado) throws RegistroException;
    public Collection<Registro> getAllRegistrosEmpleado(Integer idEmpleado) throws RegistroException;
}
