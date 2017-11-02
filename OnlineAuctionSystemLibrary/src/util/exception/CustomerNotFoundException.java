/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;


public class CustomerNotFoundException extends Exception {



    /**
     * Constructs an instance of <code>customerNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}
