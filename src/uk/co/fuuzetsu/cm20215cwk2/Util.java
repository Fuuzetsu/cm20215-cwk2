package uk.co.fuuzetsu.cm20215cwk2;

import fj.F;
import fj.Unit;
import fj.data.Either;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Util {

    private Util() {}

    public static IO<Either<String, Double>> getPositiveDouble_() {
        return new IO<Either<String, Double>>
            (new F<Unit, Either<String, Double>>() {
                @Override
                public Either<String, Double> f(Unit u) {
                    return getPositiveDouble();
                }

            });
    }

    public static IO<Either<String, Integer>> getPositiveInt_() {
        return new IO<Either<String, Integer>>
            (new F<Unit, Either<String, Integer>>() {
                @Override
                public Either<String, Integer> f(Unit u) {
                    return getPositiveDouble().right().map(Util.intValue_());
                }

            });
    }

    public static IO<Either<String, Double>> getPositiveDoubleQ_(final String s) {
        return new IO<Either<String, Double>>
            (new F<Unit, Either<String, Double>>() {
                @Override
                    public Either<String, Double> f(Unit u) {
                    return getPositiveDouble(s);
                }

            });
    }


    public static IO<Either<String, Integer>> getPositiveIntQ_(final String s) {
        return new IO<Either<String, Integer>>
            (new F<Unit, Either<String, Integer>>() {
                @Override
                public Either<String, Integer> f(Unit u) {
                    return getPositiveDouble(s).right().map(Util.intValue_());
                }

            });
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

    public static <A> F<A, IO<Unit>> print_() {
        return new F<A, IO<Unit>>() {
            @Override
            public IO<Unit> f(A a) {
                if (a == null) {
                    System.out.println("null");
                } else {
                    System.out.println(a.toString());
                }

                return IO.ret();
            }
        };
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
