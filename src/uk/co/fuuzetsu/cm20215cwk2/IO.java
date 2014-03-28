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

public class IO<A> {
    private final F<Unit, A> runF;

    public IO(final F<Unit, A> f) {
        this.runF = f;
    }

    public A run() {
        return runF.f(Unit.unit());
    }

    public static IO<Unit> ret() {
        return new IO<Unit>(new F<Unit, Unit>() {
                @Override
                public Unit f(Unit u) {
                    return Unit.unit();
                }
        });
    }
}
