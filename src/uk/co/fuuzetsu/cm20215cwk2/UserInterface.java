package uk.co.fuuzetsu.cm20215cwk2;

import fj.F;
import fj.P2;
import fj.data.Either;
import fj.Unit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    private BufferedReader reader;
    private EmployeeDatabase database;

    private static void printUsage() {
        System.out.println("usage: java UserInterface [recordFile]");
    }

    private static Either<String, List<Employee>>
        parseRecordFile(File recordFile) {

        List<Employee> employees = new ArrayList<Employee>();

        String line;
        Integer lineCount = 0;

        BufferedReader breader = null;
        try {
            FileInputStream fstream = new FileInputStream(recordFile);
            InputStreamReader in = new InputStreamReader(fstream);
            breader = new BufferedReader(in);

        }
        catch (FileNotFoundException e) {
            System.out.println(String.format("Record file %s  doesn't exist.",
                                             recordFile.getName()));
            System.exit(3);
        }

        /* Parse file line by line */
        try {
            while ((line = breader.readLine()) != null) {
                lineCount++;

                /* Ignore empty and comment lines. */
                if (line.length() == 0 || line.charAt(0) == '%')
                    continue;

                Either<String, ? extends Employee> er = RecordParser.parse(line);

                if (er.isLeft()) {
                    /* Just print the error and skip the record */
                    System.out.println(er.left().value());
                } else {
                    employees.add(er.right().value());
                }
            }
        }
        catch (IOException e) {
            return Either.left("IO Exception when read records. Aborting...");
        }

        return Either.right(employees);
    }

    public static void main(String[] args) {
        /* Load records from a text file */
        String recordFile = null;
        switch (args.length) {
        case 0: recordFile = "records.txt"; break;
        case 1: recordFile = args[0]; break;
        default: printUsage(); System.exit(2);
        }

        File f = new File(recordFile);

        if (f.exists() && !f.isDirectory()) {
            Either<String, List<Employee>> employees = parseRecordFile(f);
            if (employees.isLeft()) {
                System.out.println(employees.left().value());
                System.exit(2);
            } else {
                EmployeeDatabase dbase = new EmployeeDatabase();

                /* Populate the database */
                for (Employee emp : employees.right().value()) {
                    dbase.addEmployee(emp);
                }

                /* Make a sure to interface with our database. */
                UserInterface ui = new UserInterface(dbase);
                ui.interact();
            }
        } else {
            System.out.println("Record file " + f.getName() + " doesn't exist.");
            System.exit(3);
        }

    }

    public UserInterface(EmployeeDatabase database) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.database = database;
    }

    public void interact() {
        while (true) {
            printMainMenu();
            Integer choice = getIntFromConsole();
            switch (choice) {
            case 1 : System.out.println(database); break;
            case 2 :
                getMonthlyWage().<IO<Unit>>either(Util.<String>print_(),
                                                  Util.<Money>print_());
                break;
            case 3 : recordAddRoutine(); break;
            case 4 : recordDeleteRoutine(); break;
            case 5 : System.exit(0); break;
            default : System.out.println("Invalid choice."); continue;
            }

        }
    }

    private void recordDeleteRoutine() {
        String message = "Which record to delete? 0 to cancel.";
        Integer record = getIntFromConsole(message);
        if (record != 0) {
            boolean gotDeleted = database.deleteRecord(record);
            if (!gotDeleted)
                System.out.println("No such record.");
        }
    }

    private void recordAddRoutine() {
        String format = "ID | Name | JobTitle | PhoneNumber | ";
        format += "City : StreetAddress : Post-code";
        String lecmsg = "Lecturer performance pay should be a last field.";
        lecmsg += " No £ sign.";
        String message = String.format("The record format is:\n%s\n%s\n%s",
                                       format, lecmsg, "0 to exit.");
        System.out.println(message);
        while (true) {
            try {
                String record = reader.readLine();
                if (record == null)
                    continue;

                if (record.equals("0"))
                    break;

                Either<String, ? extends Employee> parseResult = RecordParser.parse(record);
                if (parseResult.isLeft()) {
                    System.out.println("Failed to parse input. Check your format.");
                    System.out.println(parseResult.left().value());
                }
                else {
                    database.addEmployee(parseResult.right().value());
                    break;
                }
            }
            catch (IOException e) {
                System.out.println("IO Exception when read records. Aborting...");
                System.exit(1);
            }
        }
    }


    private void printMainMenu() {
        System.out.println("1 - Print database.\n" +
                           "2 - Calculate monthly wage for an employee.\n" +
                           "3 - Add a record.\n" +
                           "4 - Delete a record.\n" +
                           "5 - Quit.");
    }

    private Integer getIntFromConsole() {
        return getDoubleFromConsole().intValue();
    }

    private Integer getIntFromConsole(String prompt) {
        System.out.println(prompt);
        return getIntFromConsole();
    }

    private Double getDoubleFromConsole() {
        while (true) {
            try {
                String s = reader.readLine();

                if (s == null)
                    continue;

                Double d = Double.parseDouble(s);
                if (d < 0) {
                    System.out.println("Please enter a number >= 0.");
                    continue;
                }
                return d;
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            catch (IOException io) {
                System.out.println("IO Exception has occured. Aborting...");
                System.exit(1);
            }
        }
    }

    public Either<String, Money> getMonthlyWage() {
        System.out.println(database);
        System.out.println("Pick an employee by database record number.");
        Integer recordNumber = getIntFromConsole();

        Either<String, Employee> empr = database.getEmployee(recordNumber);
        if (empr.isLeft()) {
            return Either.left(empr.left().value());
        } else {
            final Employee emp = empr.right().value();
            Money base = emp.getBaseMonthlySalary();
            Either<IO<Either<String, Money>>, Money> wact = emp.calculateWage();

            if (wact.isLeft()) {
                Either<String, Money> r = wact.left().value().run();
                if (r.isLeft()) {
                    return Either.left(r.left().value());
                } else {
                    return Either.right(r.right().value());
                }
            } else {
                return Either.right(wact.right().value());
            }
        }
    }
}
