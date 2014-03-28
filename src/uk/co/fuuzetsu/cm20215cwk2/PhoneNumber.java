/* This file is part of mk440-cm20215-cwk2.
 *
 * mk440-cm20215-cwk2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mk440-cm20215-cwk2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mk440-cm20215-cwk2.  If not, see <http://www.gnu.org/licenses/>.
 */
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
