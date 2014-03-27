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
