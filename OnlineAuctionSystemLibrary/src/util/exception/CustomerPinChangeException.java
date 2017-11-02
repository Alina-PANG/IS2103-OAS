/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author Amber
 */
public class CustomerPinChangeException extends Exception {

    /**
     * Creates a new instance of <code>CustomerChangePinException</code> without
     * detail message.
     */
    public CustomerPinChangeException() {
    }

    /**
     * Constructs an instance of <code>CustomerChangePinException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerPinChangeException(String msg) {
        super(msg);
    }
}
