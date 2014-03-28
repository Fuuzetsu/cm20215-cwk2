/* This file is part of mk440-cm20215-cwk2.
 *
 * mk440-cm20215-cwk2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mk440-cm20215-cwk2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mk440-cm20215-cwk2.  If not, see <http://www.gnu.org/licenses/>.
 */
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
