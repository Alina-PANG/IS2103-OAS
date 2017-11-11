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
public class AuctionClosedException extends Exception{

    public AuctionClosedException() {
    }

    public AuctionClosedException(String message) {
        super(message);
    }
    
}
