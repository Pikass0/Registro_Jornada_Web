/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c2b.jornadas.excepciones;

import javax.ejb.ApplicationException;

/**
 *
 * @author user
 */
@ApplicationException(rollback = true)
//extendemos de exception, entonces las runtimes exception las capturamos aqui y propagamos como exception
//pero no hace rollback aqui, entonces ponemos la anotación para que lo haga como si fuera runtime ex
public class RegistroException extends Exception {

    /**
     * Creates a new instance of <code>RegistroException</code> without detail
     * message.
     */
    public RegistroException() {
    }

    /**
     * Constructs an instance of <code>RegistroException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RegistroException(String msg) {
        super(msg);
    }
}
