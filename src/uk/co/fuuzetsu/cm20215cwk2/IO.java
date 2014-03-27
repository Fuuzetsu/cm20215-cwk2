package uk.co.fuuzetsu.cm20215cwk2;

import fj.F;
import fj.Unit;

public class IO<A> {
    final private F<Unit, A> runF;
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
