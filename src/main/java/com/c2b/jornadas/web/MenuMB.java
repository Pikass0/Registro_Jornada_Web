/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c2b.jornadas.web;

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
public class MenuMB {

    /**
     * Creates a new instance of MenuMB
     */
    public MenuMB() {
    }
    
    
    public void alta() {
        addMessage("Success", "prueba mensaje");
    }

    public void baja() {
        addMessage("Success", "prueba mensaje");
    }

    public void mod() {
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
}