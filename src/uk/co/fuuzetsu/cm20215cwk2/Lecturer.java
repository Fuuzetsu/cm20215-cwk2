package uk.co.fuuzetsu.cm20215cwk2;

import fj.F;
import fj.Unit;
import fj.data.Either;

public class Lecturer extends Employee {

    public Lecturer(final Integer id, final String name,
                    final Address address, final PhoneNumber phoneNumber) {
        super("Lecturer", name, id, new Salary(20000d), address, phoneNumber);
    }

    @Override
    public Either<IO<Either<String, Money>>, Money> calculateWage() {

        F<Unit, Either<String, Money>> f =
            new F<Unit, Either<String, Money>>() {

            @Override
            public Either<String, Money> f(Unit u) {
                Either<String, Double> c =
                  Util.getPositiveDouble
                    ("Enter number of consultancy hours.");

                if (c.isLeft()) {
                    return Either.left(c.left().value());
                }

                Either<String, Double> p =
                  Util.getPositiveDouble
                    ("Enter yearly performance pay.");

                if (p.isLeft()) {
                    return Either.left(p.left().value());
                }

                Money cp = new Money(c.right().value() * 20d);
                Money pp = new Salary(new Money(p.right().value())).getMonthly();

                return Either.right(getSalary().getMonthly().add(cp).add(pp));
            }


        };

        return Either.left(new IO<>(f));
    }
}
