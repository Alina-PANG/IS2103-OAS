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
public class AddressAlreadyExistsException extends Exception {

    /**
     * Creates a new instance of <code>AddressAlreadyExistsException</code>
     * without detail message.
     */
    public AddressAlreadyExistsException() {
    }

    /**
     * Constructs an instance of <code>AddressAlreadyExistsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AddressAlreadyExistsException(String msg) {
        super(msg);
    }
}
