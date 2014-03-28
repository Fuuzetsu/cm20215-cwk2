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
