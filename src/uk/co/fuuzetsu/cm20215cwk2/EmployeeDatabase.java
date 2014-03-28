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

import fj.P;
import fj.P2;
import fj.data.Either;
import java.util.List;
import java.util.ArrayList;

public class EmployeeDatabase {
    private final List<P2<Integer, Employee>> employees;
    private Integer lastRecordID = 1;

    public EmployeeDatabase() {
        employees = new ArrayList<P2<Integer, Employee>>();
    }

    public Either<String, Employee> getEmployee(Integer recordNumber) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i)._1() == recordNumber) {
                return Either.right(employees.get(i)._2());
            }
        }

        return Either.left(String.format("Record %d doesn't exist", recordNumber));
    }

    public void addEmployee(Employee e) {
        P2<Integer, Employee> record = P.p(lastRecordID, e);
        employees.add(record);
        lastRecordID += 1;
    }

    public Boolean deleteRecord(Integer recordNumber) {
        int itemIndex = -1;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i)._1() == recordNumber) {
                itemIndex = i;
                break;
            }
        }

        if (itemIndex == -1)
            return false;

        employees.remove(itemIndex);
        return true;
    }

    public Boolean doesRecordExist(Integer recordNumber) {
        for (P2<Integer, Employee> p : employees) {
            if (p._1() == recordNumber) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String text = "";

        for (P2<Integer, Employee> p : employees)
            text += String.format("%d | %s\n", p._1(), p._2());

        return text;
    }
}
