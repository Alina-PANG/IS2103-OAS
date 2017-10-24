/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.StaffEntity;
import java.util.List;
import util.exception.DuplicateException;
import util.exception.GeneralException;
import util.exception.IncorrectPasswordException;
import util.exception.StaffAlreadyExistException;
import util.exception.StaffNotFoundException;


/**
 *
 * @author alina
 */
public interface StaffEntityControllerLocal {

    public StaffEntity createNewStaffEntity(StaffEntity staff) throws StaffAlreadyExistException, GeneralException;

    public StaffEntity retrieveStaffById(Long id) throws StaffNotFoundException, GeneralException;

    public StaffEntity retrieveStaffByIdentificationNumber(String number) throws StaffNotFoundException, DuplicateException;

    public StaffEntity staffLogin(String username, String password) throws StaffNotFoundException, IncorrectPasswordException;

    public StaffEntity changePassword(String currentPw, String newPw, Long id) throws IncorrectPasswordException, StaffNotFoundException, GeneralException;

    public StaffEntity updateEmployee(StaffEntity newStaff) throws StaffNotFoundException, GeneralException;

    public void deleteEmployee(Long id) throws StaffNotFoundException, GeneralException;

    public List<StaffEntity> viewAllEmployee() throws GeneralException;
}
