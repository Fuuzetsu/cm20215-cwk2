package uk.co.fuuzetsu.cm20215cwk2;

public class GenericRecord {
    private Integer id;
    private String name, jobTitle;
    private PhoneNumber phoneNumber;
    private Address address;

    public Integer getID() { return id; }
    public String getName() { return name; }
    public String getJobTitle() { return jobTitle; }
    public PhoneNumber getPhoneNumber() { return phoneNumber; }
    public Address getAddress() { return address; }

    public GenericRecord(String[] line) throws RecordException {
        try {
            id = Integer.parseInt(line[0]);
            name = line[1];
            jobTitle = line[2];

            /* Ideally we should throw a range exception. */
            if (id <= 0)
                throw new RecordException("id value less than or 0.");

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
        }
        catch (InvalidPhoneNumberException n) {
            System.out.println(n);
            throw new RecordException(n.getMessage());
        }
        catch (ArrayIndexOutOfBoundsException b) {
            System.out.println(b);
            throw new RecordException("Unexpected address format.");
        }
        catch (NumberFormatException nf) {
            System.out.println(nf);
            throw new RecordException("Unexpected id format.");
        }

    }
}
