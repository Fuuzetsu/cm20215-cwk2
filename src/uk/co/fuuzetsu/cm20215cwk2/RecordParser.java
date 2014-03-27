package uk.co.fuuzetsu.cm20215cwk2;

import fj.data.Either;

public class RecordParser {

    /* Either a parse error or an employee */
    public static Either<String, ? extends Employee> parse(String recordLine) {
        String[] lineContent = recordLine.split(" \\| ");

        GenericRecord gr;
        try {
            gr = new GenericRecord(lineContent);
        }
        catch (RecordException e) {
            return Either.left(e.getMessage());
        }

        /* if-else because non-integer switch is clearly too advanced */
        if (gr.getJobTitle().equals("Researcher")) {
            return Either.right(parseResearcher(gr));
        } else if (gr.getJobTitle().equals("Administrator")) {
            return Either.right(parseAdministrator(gr));
        } else if (gr.getJobTitle().equals("Lecturer")) {
            return Either.right(parseLecturer(gr, lineContent));
        } else {
            return Either.left("Can't parse job title near: " + recordLine);
        }
    }

    private static Employee parseResearcher(GenericRecord gr) {
        return new Researcher(gr.getID(), gr.getName(),
                              gr.getAddress(), gr.getPhoneNumber());
    }

    private static Employee
        parseAdministrator(GenericRecord gr) {
        return new Administrator(gr.getID(), gr.getName(),
                                 gr.getAddress(), gr.getPhoneNumber());
    }

    private static Employee
        parseLecturer(GenericRecord gr, String[] lineContent) {
        return new Lecturer(gr.getID(), gr.getName(),
                            gr.getAddress(), gr.getPhoneNumber());
    }
}
