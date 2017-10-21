/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author alina
 */
public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException() {
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }
    
}
