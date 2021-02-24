/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c2b.jornadas.web;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author user
 */
@Named(value = "menuMB")
@ViewScoped
public class MenuMB implements Serializable{

    //para saber la view en la que estoy siempre
    private String view = "login";
    private boolean modificar = false;
    /**
     * Creates a new instance of MenuMB
     */
    public MenuMB() {
    }
    
    
    public String alta() {
        view = "alta";
        modificar = false;
        return view;

    }

    public String baja() {
        view = "baja";
        return view;
    }

    
    public void modif() {
        view = "modif";
        modificar = true;
        addMessage("Success", "prueba mensaje");
    }
    public void horarios() {
        addMessage("Success", "prueba mensaje");
    }
    public void empleados() {
        addMessage("Success", "prueba mensaje");
    }
    public void info() {
        addMessage("Success", "prueba mensaje");
    }
    public void logout() {
        addMessage("Success", "prueba mensaje");
    }
    

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    
    
}
