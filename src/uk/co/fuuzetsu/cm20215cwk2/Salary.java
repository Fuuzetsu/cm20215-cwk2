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
