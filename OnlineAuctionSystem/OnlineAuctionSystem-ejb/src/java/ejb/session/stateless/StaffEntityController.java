/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.StaffEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.enumeration.EmployeeAccessRightEnum;
import util.exception.GeneralException;
import util.exception.IncorrectPasswordException;
import util.exception.StaffAlreadyExistException;
import util.exception.StaffNotFoundException;

/**
 *
 * @author alina
 */
@Stateless
@Local(StaffEntityControllerLocal.class)
@Remote(StaffEntityControllerRemote.class)
public class StaffEntityController implements StaffEntityControllerRemote, StaffEntityControllerLocal {

    @PersistenceContext(unitName = "OnlineAuctionSystem-ejbPU")
    private EntityManager em;

    @Override
    public StaffEntity createNewStaffEntity(StaffEntity staff) throws StaffAlreadyExistException, GeneralException {
        try {
            em.persist(staff);
            em.flush();
            em.refresh(staff);

            return staff;
        } catch (PersistenceException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
                throw new StaffAlreadyExistException("Staff with same identification number already exist!");
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        }

    }

    @Override
    public StaffEntity retrieveStaffById(Long id) throws StaffNotFoundException, GeneralException {
        // retrieve the staff
        StaffEntity staff = em.find(StaffEntity.class, id);

        // check and throw exception
        if (staff == null) {
            throw new StaffNotFoundException("Staff with id = " + id + " does not exist!");
        } else {
            return staff;
        }
    }

    @Override
    public StaffEntity retrieveStaffByIdentificationNumber(String number) throws StaffNotFoundException {
        // retrieve staff
        Query query = em.createQuery("SELECT s FROM STAFFENTITY s WHERE s.identificationNumber == :num");
        query.setParameter("num", number);

        try {
            return (StaffEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new StaffNotFoundException("Staff with identification number = " + number + " is not found!");
        }
    }

    @Override
    public EmployeeAccessRightEnum staffLogin(String username, String password) throws StaffNotFoundException, IncorrectPasswordException {
        // retrieve staff
        Query query = em.createQuery("SELECT s FROM STAFFENTITY s WHERE :name = s.username");
        query.setParameter("name", username);

        try {
            StaffEntity staff = (StaffEntity) query.getSingleResult();
            if (staff.getPassword().equals(password)) {
                return staff.getAccessRight();
            } else {
                throw new IncorrectPasswordException("Incorrect Password!");
            }
        } catch (NoResultException ex) {
            throw new StaffNotFoundException("Username does not exist!");
        }
    }

    @Override
    public void changePassword(String currentPw, String newPw, Long id) throws IncorrectPasswordException, StaffNotFoundException, GeneralException {
        StaffEntity staff = retrieveStaffById(id);

        if (staff.getPassword().equals(currentPw)) {
            staff.setPassword(newPw);
        } else {
            throw new IncorrectPasswordException("You must insert correct old password to change your new password!");
        }
    }

    // manager can only update the firstname, lastname, accessRight, identificationNumber. others are cretical information, and must be hidden from the manager.
    @Override
    public StaffEntity updateEmployee(StaffEntity newStaff) throws StaffNotFoundException, GeneralException{
        StaffEntity oldStaff = retrieveStaffById(newStaff.getId());

        oldStaff.setIdentificationNumber(newStaff.getIdentificationNumber());
        oldStaff.setLastName(newStaff.getLastName());
        oldStaff.setAccessRight(newStaff.getAccessRight());
        oldStaff.setFirstName(newStaff.getFirstName());
        
        em.flush();
        em.refresh(oldStaff);
        
        return oldStaff;
    }

    @Override
    public void deleteEmployee(Long id) throws StaffNotFoundException, GeneralException{
        StaffEntity staff = retrieveStaffById(id);
        
        em.remove(staff);
    }
    
    @Override
    public List<StaffEntity> viewAllEmployee () throws GeneralException{
        Query query = em.createQuery("SELECT * FROM STAFFENTITY s");
        try{
        return (List<StaffEntity>) query.getResultList();
        } catch (Exception ex){
            throw new GeneralException ("An unexpected exception happens: "+ex.getMessage());
        }
    }
}
