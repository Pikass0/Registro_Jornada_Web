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


    /**
     * Creates a new instance of MenuMB
     */
    public MenuMB() {
    }
    
    
    public String alta() {
        
        return "alta";

    }

    public String buscar() {
        return "buscar";
    }

    
    public String modif() {

        return "modif";
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
