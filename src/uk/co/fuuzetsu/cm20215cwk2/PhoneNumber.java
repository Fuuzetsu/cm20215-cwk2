package uk.co.fuuzetsu.cm20215cwk2;

import fj.data.Either;

public final class PhoneNumber {
    private final String number;

    private PhoneNumber(final String number) {
        this.number = number;
    }

    public static Either<String, PhoneNumber>
        phoneNumber(final String number) {
        if (isValid(number)) {
            return Either.right(new PhoneNumber(number));
        } else {
            return Either.left
                (String.format("%s is not a valid phone number.", number));
        }
    }

    public String getNumber() {
        return number;
    }

    public String toString() {
        return number;
    }

    /* Empty numbers are actually OK. */
    private static Boolean isValid(final String n) {
        for (Character c : n.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }
}
