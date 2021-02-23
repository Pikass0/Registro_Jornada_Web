/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c2b.jornadas.excepciones;

/**
 *
 * @author user
 */
public class EmpleadoException extends Exception {

    /**
     * Creates a new instance of <code>EmpleadoException</code> without detail
     * message.
     */
    public EmpleadoException() {
    }

    /**
     * Constructs an instance of <code>EmpleadoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmpleadoException(String msg) {
        super(msg);
    }
}
