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
public class CustomerAlreadyPremiumException extends Exception{

    public CustomerAlreadyPremiumException() {
    }

    public CustomerAlreadyPremiumException(String message) {
        super(message);
    }
    
}
