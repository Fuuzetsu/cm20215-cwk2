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

public final class GenericRecord {
    private final Integer id;
    private final String name, jobTitle;
    private final PhoneNumber phoneNumber;
    private final Address address;

    public Integer getID() { return id; }
    public String getName() { return name; }
    public String getJobTitle() { return jobTitle; }
    public PhoneNumber getPhoneNumber() { return phoneNumber; }
    public Address getAddress() { return address; }

    private GenericRecord(final Integer id, final String name,
                          final String jobTitle, final PhoneNumber phoneNumber,
                          final Address address) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static Either<String, GenericRecord> genericRecord(String[] line) {
        try {
            Integer id = Integer.parseInt(line[0]);
            String name = line[1];
            String jobTitle = line[2];
            Either<String, PhoneNumber> phoneNumber;
            Address address;

            if (id <= 0) {
                return Either.left("ID value less than or 0.");
            }

            phoneNumber = PhoneNumber.phoneNumber(line[3]);


            String[] ap = line[4].split(" : ");
            address = new Address(ap[0], ap[1], ap[2]);

            if (phoneNumber.isLeft()) {
                return Either.left(phoneNumber.left().value());
            } else {
                return Either.right
                    (new GenericRecord(id, name, jobTitle,
                                       phoneNumber.right().value(), address));
            }

        }
        catch (ArrayIndexOutOfBoundsException b) {
            System.out.println(b);
            return Either.left("Unexpected address format.");
        }
        catch (NumberFormatException nf) {
            System.out.println(nf);
            return Either.left("Unexpected id format.");
        }

    }
}
