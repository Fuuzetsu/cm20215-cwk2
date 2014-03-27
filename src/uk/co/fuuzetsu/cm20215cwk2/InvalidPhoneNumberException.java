package uk.co.fuuzetsu.cm20215cwk2;

public class InvalidPhoneNumberException extends Exception {
    static final long serialVersionUID = -5132228254676355816L;
    String badNumber;
    public InvalidPhoneNumberException(String number) {
        badNumber = number;
    }

    public String getMessage() {
        return String.format("%s is not a valid phone number.", badNumber);
    }
}
