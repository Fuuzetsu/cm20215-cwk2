package uk.co.fuuzetsu.cm20215cwk2;

import fj.P;
import fj.P2;

public class RecordParser {

    /* Returns a pair of employee (if any) and parsing success bool. */
    public static P2<? extends Employee, Boolean> parse(String recordLine) {
        String[] lineContent = recordLine.split(" \\| ");

        GenericRecord gr;
        try {
            gr = new GenericRecord(lineContent);
        }
        catch (RecordException e) {
            return P.p(null, false);
        }

        P2<? extends Employee, Boolean> parseResult;
        /* if-else because non-integer switch is clearly too advanced */
        if (gr.getJobTitle().equals("Researcher")) {
            parseResult = parseResearcher(gr);
        }
        else if (gr.getJobTitle().equals("Administrator")) {
            parseResult = parseAdministrator(gr);
        }
        else if (gr.getJobTitle().equals("Lecturer")) {
            parseResult = parseLecturer(gr, lineContent);
        }
        else {
            return P.p(null, false);
        }
        return parseResult;
    }

    private static P2<? extends Employee, Boolean> parseResearcher(GenericRecord gr) {
        Researcher r = new Researcher(gr.getID(), gr.getName(),
                                      gr.getAddress(), gr.getPhoneNumber());

        return P.p(r, true);
    }

    private static P2<? extends Employee, Boolean>
        parseAdministrator(GenericRecord gr) {
        Administrator a =
            new Administrator(gr.getID(), gr.getName(),
                              gr.getAddress(), gr.getPhoneNumber());

        return P.p(a, true);
    }

    private static P2<? extends Employee, Boolean>
        parseLecturer(GenericRecord gr, String[] lineContent) {
        /* Assume that performance pay is the last field for lecturers. */
        String payString = lineContent[lineContent.length - 1];
        Money performance;
        try {
            performance = new Money(Double.parseDouble(payString));
        }
        catch (NumberFormatException e) {
            return P.p(null, false);
        }

        if (performance.getAmount() < 0)
            return P.p(null, false);

        Lecturer l;
        l = new Lecturer(gr.getID(), gr.getName(), gr.getAddress(),
                         gr.getPhoneNumber());// , performance);

        return P.p(l, true);
    }
}
