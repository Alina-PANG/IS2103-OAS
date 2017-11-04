/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AddressEntity;
import entity.CustomerEntity;
import java.util.List;
import util.exception.AddressAlreadyExistsException;
import util.exception.AddressNotFoundException;


/**
 *
 * @author alina
 */
public interface AddressEntityControllerRemote {
    public AddressEntity createAddress(AddressEntity address) throws AddressAlreadyExistsException;
     public AddressEntity getAddressById(Long addressid);
     public List<AddressEntity> viewAllAddress(CustomerEntity customer);
     public boolean deleteAddress(Long id) throws AddressNotFoundException;

    
    
}
