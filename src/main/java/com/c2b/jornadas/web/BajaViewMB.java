/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c2b.jornadas.web;

import com.c2b.jornadas.excepciones.EmpleadoException;
import com.c2b.jornadas.modelo.Empleado;
import com.c2b.jornadas.servicios.EmpleadoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author user
 */
@Named(value = "bajaViewMB")
@ViewScoped
public class BajaViewMB implements Serializable {

    @EJB
    private EmpleadoService servicio;

    private Empleado empleadoBuscar;

    private List<Empleado> empleadosSeleccionados;
    private List<Empleado> empleadosEncontrados;

    private static Logger log = Logger.getLogger("BajaViewBean");

    /**
     * Creates a new instance of DataTableMB
     */
    public BajaViewMB() {
        empleadoBuscar = new Empleado();
        //por defecto mostrar todos
        empleadosEncontrados = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        empleadosEncontrados = (List<Empleado>) servicio.getAllEmpleados();
    }

    public void buscar() {
        log.log(Level.INFO, "paso por buscar");
        try {
            empleadosEncontrados = (List<Empleado>) servicio.buscarEmploadoPorCriterio(empleadoBuscar.getNombre());

        } catch (EmpleadoException ex) {
            Logger.getLogger(EmpleadoException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void darBaja(boolean activo) {
        log.log(Level.INFO, "paso por buscar");
        try {
            servicio.baja(empleadosSeleccionados, activo);

        } catch (EmpleadoException ex) {
            Logger.getLogger(EmpleadoException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modif() {
        
       
    }

    public void onRowSelect(SelectEvent<Empleado> event) {
        Empleado eSeleccionado = event.getObject();
        empleadosSeleccionados.add(eSeleccionado);
    }

    public void onRowUnselect(UnselectEvent<Empleado> event) {
        Empleado eSeleccionado = event.getObject();
        empleadosSeleccionados.remove(eSeleccionado);

    }

    public List<Empleado> getEmpleadosEncontrados() {
        return empleadosEncontrados;
    }

    public void setEmpleadosEncontrados(List<Empleado> empleadosEncontrados) {
        this.empleadosEncontrados = empleadosEncontrados;
    }

    public Empleado getEmpleadoBuscar() {
        return empleadoBuscar;
    }

    public void setEmpleadoBuscar(Empleado empleadoBuscar) {
        this.empleadoBuscar = empleadoBuscar;
    }

    public List<Empleado> getEmpleadosSeleccionados() {
        return empleadosSeleccionados;
    }

    public void setEmpleadosSeleccionados(List<Empleado> empleadosSeleccionados) {
        this.empleadosSeleccionados = empleadosSeleccionados;
    }

}
