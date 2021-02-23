package com.c2b.jornadas.web;

import com.c2b.jornadas.excepciones.EmpleadoException;
import com.c2b.jornadas.modelo.Empleado;
import com.c2b.jornadas.servicios.EmpleadoService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "empleadoMB")
@SessionScoped
public class EmpleadoMB implements Serializable {

    private Empleado usuarioLogueado;
    private Empleado empleado;
    private String inputDni;
    private String inputClave;

    private Date fechaHoy = new Date();
    private double precio = 33.2;

    @EJB
    private EmpleadoService servicio;

    private static Logger log = Logger.getLogger("EmpleadoManagedBean");

    public EmpleadoMB() {
        usuarioLogueado = new Empleado();
    }

    public void guardarCambios() {
        try {
            servicio.modificar(empleado);
        } catch (EmpleadoException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public String login() {
        // BD - ir a buscar usuario y claeveç
        System.out.printf("login %s   y clave %s", inputDni, inputClave);

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) ctx.getExternalContext().getSession(true);

        try {
            usuarioLogueado = servicio.login(inputDni, inputClave, sesion);
            return "home";
        } catch (EmpleadoException ex) {
            log.log(Level.SEVERE, null, ex);
            inputDni = "";
            inputClave = "";

            //mensaje de error
            FacesMessage msg = new FacesMessage(ex.getMessage());
            //ctx.addMessage("formLogin:pwd", msg);
            ctx.addMessage(null, msg);
            return "login";
        }

    }

    public String logout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) ctx.getExternalContext().getSession(true);
        sesion.invalidate();
        
        usuarioLogueado = null;

        return "login";
    }
    
    public void alta(){
        
    }

    //GET/SET

    public Empleado getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Empleado usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
    public String getInputClave() {
        return inputClave;
    }

    public void setInputClave(String clave) {
        this.inputClave = clave;
    }

    public String getInputDni() {
        return inputDni;
    }

    public void setInputDni(String dni) {
        this.inputDni = dni;
    }

    public Date getFecha() {
        return fechaHoy;
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


}
