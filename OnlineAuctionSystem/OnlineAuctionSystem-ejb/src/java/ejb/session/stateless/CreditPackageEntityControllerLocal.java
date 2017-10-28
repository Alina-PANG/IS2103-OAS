/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CreditPackageEntity;
import java.util.List;
import util.exception.CreditPackageAlreadyExistException;
import util.exception.CreditPackageNotFoundException;
import util.exception.GeneralException;

/**
 *
 * @author alina
 */
public interface CreditPackageEntityControllerLocal {

    public CreditPackageEntity updateCreditPackage(CreditPackageEntity newCp) throws CreditPackageNotFoundException, GeneralException, CreditPackageAlreadyExistException;

    public CreditPackageEntity createNewCreditPackage(CreditPackageEntity cp) throws CreditPackageAlreadyExistException, GeneralException;

    public CreditPackageEntity retrieveCreditPackageById(Long id) throws CreditPackageNotFoundException;

    public List<CreditPackageEntity> retrieveCreditPackageByName(String name) throws CreditPackageNotFoundException;

    public List<CreditPackageEntity> viewAllCreditPackage() throws GeneralException;

    public boolean deleteCreditPackage(Long id) throws CreditPackageNotFoundException;

}
