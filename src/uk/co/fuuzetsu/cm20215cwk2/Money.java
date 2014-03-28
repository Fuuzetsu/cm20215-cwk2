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

import fj.F;
import java.text.DecimalFormat;

public class Money {
    private final Double amount;

    public Money(final Double amount) {
        this.amount = amount;
    }


    public static F<Double, Money> money_() {
        return new F<Double, Money>() {
            @Override
            public Money f(Double m) {
                return new Money(m);
            }

        };
    }


    public double getAmount() {
        return amount;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "£" + df.format(amount);
    }

    public Money add(Money m) {
        return new Money(amount + m.getAmount());
    }

    public Money add(Double d) {
        return new Money(amount + d);
    }

}
