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
public class CreditPackageAlreadyExistException extends Exception {

    public CreditPackageAlreadyExistException() {
    }

    public CreditPackageAlreadyExistException(String message) {
        super(message);
    }
    
}
