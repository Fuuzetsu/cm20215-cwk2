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
