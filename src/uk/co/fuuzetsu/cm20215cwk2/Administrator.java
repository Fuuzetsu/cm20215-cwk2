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
import fj.Unit;
import fj.data.Either;

public class Administrator extends Employee {

    public Administrator(final Integer id, final String name,
                         final Address address, final PhoneNumber phoneNumber) {
        super("Administrator", name, id, new Salary(15000d), address, phoneNumber);
    }

    @Override
    public Either<IO<Either<String, Money>>, Money> calculateWage() {

        F<Unit, Either<String, Money>> f =
            new F<Unit, Either<String, Money>>() {

            @Override
            public Either<String, Money> f(Unit u) {
                Either<String, Double> c =
                  Util.getPositiveDouble
                    ("Enter number of overtime hours worked.");

                if (c.isRight()) {
                    Money wage =
                        getSalary().getMonthly().add(c.right().value() * 10d);
                    return Either.right(wage);
                } else {
                    return Either.left(c.left().value());
                }
            }
        };

        return Either.left(new IO<>(f));
    }
}
