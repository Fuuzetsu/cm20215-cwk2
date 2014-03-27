package uk.co.fuuzetsu.cm20215cwk2;

public class PhoneNumber {
    final private String number;

    public PhoneNumber(final String number) throws InvalidPhoneNumberException {
        if (!isValidPhoneNumber(number))
            throw new InvalidPhoneNumberException(number);

        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String toString() {
        return number;
    }

    /* Empty numbers are actually OK. */
    private Boolean isValidPhoneNumber(final String n) {
        for (Character c : n.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }
}
