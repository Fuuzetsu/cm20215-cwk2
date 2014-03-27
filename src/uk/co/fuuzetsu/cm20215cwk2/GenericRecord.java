package uk.co.fuuzetsu.cm20215cwk2;

import fj.data.Either;

public class GenericRecord {
    final private Integer id;
    final private String name, jobTitle;
    final private PhoneNumber phoneNumber;
    final private Address address;

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
            PhoneNumber phoneNumber;
            Address address;

            if (id <= 0) {
                return Either.left("ID value less than or 0.");
            }

            try {
                phoneNumber = new PhoneNumber(line[3]);
            }
            catch (InvalidPhoneNumberException e) {
                throw e;
            }
            try {
                String[] ap = line[4].split(" : ");
                address = new Address(ap[0], ap[1], ap[2]);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                throw e;
            }
            return Either.right(new GenericRecord(id, name, jobTitle, phoneNumber, address));
        }
        catch (InvalidPhoneNumberException n) {
            System.out.println(n);
            return Either.left(n.getMessage());
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
