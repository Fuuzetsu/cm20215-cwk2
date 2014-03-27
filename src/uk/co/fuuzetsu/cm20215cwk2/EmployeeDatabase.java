package uk.co.fuuzetsu.cm20215cwk2;

import fj.P;
import fj.P2;
import java.util.List;
import java.util.ArrayList;

public class EmployeeDatabase {
    private List<P2<Integer, Employee> > employees;
    private Integer lastRecordID = 1;

    public EmployeeDatabase() {
        employees = new ArrayList<P2<Integer, Employee>>();
    }

    public Employee getEmployee(Integer recordNumber) throws InvalidRecordID {
        for (int i = 0; i < employees.size(); i++)
            if (employees.get(i)._1() == recordNumber)
                return employees.get(i)._2();

        String msg = String.format("Record %d doesn't exist", recordNumber);
        throw (new InvalidRecordID(msg));

    }

    public void addEmployee(Employee e) {
        P2<Integer, Employee> record = P.p(lastRecordID, e);
        employees.add(record);
        lastRecordID += 1;
    }

    public boolean deleteRecord(Integer recordNumber) {
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

    public boolean doesRecordExist(Integer recordNumber) {
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
