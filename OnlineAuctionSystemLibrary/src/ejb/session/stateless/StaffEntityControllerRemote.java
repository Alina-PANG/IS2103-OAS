/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.StaffEntity;
import java.util.List;
import util.enumeration.EmployeeAccessRightEnum;
import util.exception.GeneralException;
import util.exception.IncorrectPasswordException;
import util.exception.StaffAlreadyExistException;
import util.exception.StaffNotFoundException;

/**
 *
 * @author alina
 */
public interface StaffEntityControllerRemote {

    public StaffEntity createNewStaffEntity(StaffEntity staff) throws StaffAlreadyExistException, GeneralException;

    public StaffEntity retrieveStaffById(Long id) throws StaffNotFoundException, GeneralException;

    public StaffEntity retrieveStaffByIdentificationNumber(String number) throws StaffNotFoundException;

    public EmployeeAccessRightEnum staffLogin(String username, String password) throws StaffNotFoundException, IncorrectPasswordException;

    public void changePassword(String currentPw, String newPw, Long id) throws IncorrectPasswordException, StaffNotFoundException, GeneralException;

    public StaffEntity updateEmployee(StaffEntity newStaff) throws StaffNotFoundException, GeneralException;

    public void deleteEmployee(Long id) throws StaffNotFoundException, GeneralException;

    public List<StaffEntity> viewAllEmployee() throws GeneralException;
    
}
