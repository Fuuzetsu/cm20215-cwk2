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

public class Salary {
    private final Money yearlySalary;

    public Salary(Money yearly) {
        yearlySalary = yearly;
    }

    public Salary(Double d) {
        yearlySalary = new Money(d);
    }

    public static F<Money, Salary> salary_() {
        return new F<Money, Salary>() {
            @Override
            public Salary f(Money m) {
                return new Salary(m);
            }

        };
    }

    public Money getYearly() {
        return yearlySalary;
    }

    public Money getMonthly() {
        return new Money(yearlySalary.getAmount() / 12);
    }

    public String toString() {
        return yearlySalary.toString();
    }
}
