package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import util.enumeration.EmployeeAccessRightEnum;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-20T20:43:43")
@StaticMetamodel(EmployeeEntity.class)
public class EmployeeEntity_ { 

    public static volatile SingularAttribute<EmployeeEntity, String> firstName;
    public static volatile SingularAttribute<EmployeeEntity, String> lastName;
    public static volatile SingularAttribute<EmployeeEntity, String> password;
    public static volatile SingularAttribute<EmployeeEntity, EmployeeAccessRightEnum> accessRight;
    public static volatile SingularAttribute<EmployeeEntity, String> identificationNumber;
    public static volatile SingularAttribute<EmployeeEntity, Long> id;
    public static volatile SingularAttribute<EmployeeEntity, String> username;

}