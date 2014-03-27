package uk.co.fuuzetsu.cm20215cwk2;

import fj.F;
import fj.Unit;
import fj.data.Either;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Util {

    private Util() {}

    public static <A, B, C> Either<A, C> ebind(Either<A,B> e, F<B, Either<A, C>> f) {
        if (e.isLeft()) {
            return Either.left(e.left().value());
        } else {
            return f.f(e.right().value());
        }
    }

    public static F<Unit, Either<String, Double>> getPositiveDouble_() {
        return new F<Unit, Either<String, Double>>() {
            @Override
            public Either<String, Double> f(Unit u) {
                return getPositiveDouble();
            }

        };
    }

    public static F<Unit, Either<String, Integer>> getPositiveInt_() {
        return new F<Unit, Either<String, Integer>>() {
            @Override
            public Either<String, Integer> f(Unit u) {
                return getPositiveDouble().right().map(Util.intValue_());
            }

        };
    }

    public static F<String, Either<String, Double>> getPositiveDoubleQ_() {
        return new F<String, Either<String, Double>>() {
            @Override
            public Either<String, Double> f(String s) {
                return getPositiveDouble(s);
            }

        };
    }

    public static F<String, Either<String, Integer>> getPositiveIntQ_() {
        return new F<String, Either<String, Integer>>() {
            @Override
            public Either<String, Integer> f(String s) {
                return getPositiveDouble(s).right().map(Util.intValue_());
            }

        };
    }

    public static F<Double, Integer> intValue_() {
        return new F<Double, Integer>() {
            @Override
            public Integer f(Double d) {
                return d.intValue();
            }
        };
    }


    public static Either<String, Double> getPositiveDouble(String q) {
        System.out.println(q);
        return getPositiveDouble();
    }

    public static Either<String, Double> getPositiveDouble() {
        BufferedReader r =
            new BufferedReader(new InputStreamReader(System.in));
        while (true) {

            try {
                String s = r.readLine();

                if (s == null) {
                    continue;
                } else {
                    Double d = Double.parseDouble(s);
                    if (d < 0) {
                        System.out.println("Please enter a number >= 0.");
                        continue;
                    } else {
                        return Either.right(d);
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
                continue;
            } catch (IOException io) {
                return Either.left("IOException has occured. Aborting input.");
            }
        }
    }
}
