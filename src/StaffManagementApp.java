import services.Manager;
import model.StaffManager;
import services.*;

import java.util.Scanner;

public class StaffManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // implement fileReader/fileWriter to handle saving into csv/json
        CSVReader reader = new CSVReader();
        CSVWriter writer = new CSVWriter();
        Service service = new StaffService(reader, writer);
        Manager manager = new StaffManager(service);
        System.out.println("Welcome to Staff Management System");
        displayCommands();
        String command = scanner.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            manager.execute(command);
            command = scanner.nextLine();
            if(command.contains("exit")) {
                isRunning = false;
            }
        }
    }

    public static void displayCommands() {
         final String COMMANDS =
                "To use the menu, first you would need to type out the desired command as it is shown\n" +
                "and then to follow it up with the parameters and format shown below the command.\n" +
                "You can use the following commands:\n\n" +
                "Add Employee - To add a newly hired employee\n" +
                "{FirstName LastName},{Department,Position},{Salary}\n\n" +
                "Edit {id_employee} - Edit information of an existing employee\n" +
                "{FirstName LastName},{Department},{Position},{Salary}\n\n" +
                "List employees - To get a list with all employees\n\n" +
                "Search Department {Department name} - list of employees for a certain department\n\n" +
                "Search Name {FirstName} - list of employees with selected First name\n";
        System.out.println(COMMANDS);
    }
}