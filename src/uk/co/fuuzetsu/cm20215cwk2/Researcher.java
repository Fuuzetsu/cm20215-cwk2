package uk.co.fuuzetsu.cm20215cwk2;

import fj.F;
import fj.Unit;
import fj.data.Either;

public class Researcher extends Employee {

    public Researcher(final String name, final Integer id,
                      final Address address,
                      final PhoneNumber phoneNumber) {
        super("Researcher", name, id, new Salary(new Money(10000d)),
              address, phoneNumber);
    }

    @Override
    public Either<IO<Either<String, Money>>, Money> calculateWage() {
        return Either.right(getSalary().getMonthly());
    }
}
