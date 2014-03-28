package uk.co.fuuzetsu.cm20215cwk2;

import fj.data.Either;

public final class RecordParser {

    private RecordParser() {}

    /* Either a parse error or an employee */
    public static Either<String, ? extends Employee> parse(String recordLine) {
        String[] lineContent = recordLine.split(" \\| ");

        Either<String, GenericRecord> gre = GenericRecord.genericRecord(lineContent);
        if (gre.isLeft()) {
            return Either.left(gre.left().value());
        } else {
            GenericRecord gr = gre.right().value();
            switch (gr.getJobTitle()) {
            case "Researcher": return Either.right(parseResearcher(gr));
            case "Administrator": return Either.right(parseAdministrator(gr));
            case "Lecturer": return Either.right(parseLecturer(gr));
            default: return Either.left("Can't parse job title near: " + recordLine);
            }
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
        parseLecturer(GenericRecord gr) {
        return new Lecturer(gr.getID(), gr.getName(),
                            gr.getAddress(), gr.getPhoneNumber());
    }
}
