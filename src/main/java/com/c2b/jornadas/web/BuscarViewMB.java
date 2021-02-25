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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author user
 */
@Named(value = "buscarViewMB")
@ViewScoped
public class BuscarViewMB implements Serializable {

    @EJB
    private EmpleadoService servicio;

    private Empleado empleadoEditar;
    private Empleado empleadoBuscar;
    private boolean extra = true;

    private List<Empleado> empleadosSeleccionados;
    private List<Empleado> empleadosEncontrados;

    private static Logger log = Logger.getLogger("BajaViewBean");

    /**
     * Creates a new instance of DataTableMB
     */
    public BuscarViewMB() {
        empleadoBuscar = new Empleado();
        //por defecto mostrar todos
        empleadosEncontrados = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        empleadosEncontrados = (List<Empleado>) servicio.getAllEmpleados();
    }

    public void buscar() {

        try {
            empleadosEncontrados = (List<Empleado>) servicio.buscarEmploadoPorCriterio(empleadoBuscar.getNombre());
            log.log(Level.INFO, "busco");
        } catch (EmpleadoException ex) {
            Logger.getLogger(EmpleadoException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void darBaja(boolean activo) {

        try {
            servicio.baja(empleadosSeleccionados, activo);
            log.log(Level.INFO, "doy de baja");
        } catch (EmpleadoException ex) {
            Logger.getLogger(EmpleadoException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void switchExtra() {
        extra = !extra;
        log.log(Level.INFO, "cambio extra");
    }

    public void modif() {

        try {
            for (Empleado e : empleadosEncontrados) {
                log.log(Level.INFO, e.getApellidos());
                servicio.modificar(e);
            }
        } catch (EmpleadoException ex) {
            log.log(Level.SEVERE, null, ex);
        }

    }

    public void onRowSelect(SelectEvent<Empleado> event) {
        Empleado eSeleccionado = event.getObject();
        empleadosSeleccionados.add(eSeleccionado);
    }

    public void onRowUnselect(UnselectEvent<Empleado> event) {
        Empleado eSeleccionado = event.getObject();
        empleadosSeleccionados.remove(eSeleccionado);

    }

    public void onRowEdit(RowEditEvent<Empleado> event) {

    }

    public void onRowCancel(RowEditEvent<Empleado> event) {

    }

    public void onCellEdit(CellEditEvent event) {

    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    public Empleado getEmpleadoSeleccionado() {
        return empleadoEditar;
    }

    public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
        this.empleadoEditar = empleadoSeleccionado;
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
