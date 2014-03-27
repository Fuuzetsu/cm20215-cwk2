package uk.co.fuuzetsu.cm20215cwk2;

public class Address {
    final private String town, street, postcode;

    public Address(final String town, final String street,
                   final String postcode) {
        this.town = town;
        this.street = street;
        this.postcode = postcode;
    }

    public String getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public String getPostcode() {
        return postcode;
    }

    public String toString() {
        return street + ", " + town + ", " + postcode;
    }
}
