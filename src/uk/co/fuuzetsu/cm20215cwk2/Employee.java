package uk.co.fuuzetsu.cm20215cwk2;

import fj.data.Either;

public abstract class Employee {
    final private String jobTitle, name;
    final private Integer id;
    final private Salary yearlySalary;
    final private Address address;
    final private PhoneNumber phoneNumber;

    public Employee(final String jobTitle, final String name, final Integer id,
                    final Salary salary, final Address address,
                    final PhoneNumber phoneNumber) {
        this.jobTitle = jobTitle;
        this.id = id;
        this.name = name;
        yearlySalary = salary;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return String.format("ID: %d | %s | %s | %s | %s | %s", id, name,
                             jobTitle, yearlySalary, phoneNumber, address);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getName() {
        return name;
    }

    public Integer getEmployeeid() {
        return id;
    }

    public Salary getSalary() {
        return yearlySalary;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Money getBaseMonthlySalary() {
        return yearlySalary.getMonthly();
    }

    abstract public Either<IO<Either<String, Money>>, Money> calculateWage();

}
