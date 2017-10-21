package util.exception;



public class StaffAlreadyExistException extends Exception
{

    public StaffAlreadyExistException() {
    }

    public StaffAlreadyExistException(String message) {
        super(message);
    }

}