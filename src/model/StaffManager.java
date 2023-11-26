package model;

import services.*;

import java.io.Serializable;
import java.io.Writer;
import java.time.LocalDate;
import java.util.*;

public class StaffManager implements Manager {
    Service service;
    List<Serializable> employees = new ArrayList<>();
    String filePath;
    public StaffManager(Service service) {
        this.service = service;
        this.filePath = "resources/employees_info.csv";
    }

    @Override
    public void execute(String command) {
        CSVReader reader = new CSVReader();
        CSVWriter writer = new CSVWriter();
        StaffService staffService = new StaffService(reader,writer);
        employees = (List<Serializable>) reader.read(filePath);
        Scanner scanner = new Scanner(System.in);

        if(command.equalsIgnoreCase("add employee")) {
            addEmployee();
        } else if(command.contains("Edit")) {
            String[] values = command.split(" ");
            try{
                editEmployee(Integer.parseInt(values[1]));
            } catch (IndexOutOfBoundsException exception) {
                exception.printStackTrace();
            }
        } else if(command.contains("List") || command.contains("list")) {
            for(Serializable emp : employees) {
                System.out.println(emp.toString());
            }
        } else if(command.contains("Search Department")) {
            try {
                String[] values = scanner.nextLine().split(" ");
                System.out.println(searchDepartment(values[2]));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } else if(command.contains("Search ID")) {
            try {
                String[] values = scanner.nextLine().split(" ");
                searchEmployee(Integer.parseInt(values[2]));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } else if(command.contains("Fire")) {
            try{
                String[] values = scanner.nextLine().split(" ");
                fireEmployee(Integer.parseInt(values[1]));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } else if(command.contains("Search Name")) {
            try{
                String[] values = scanner.nextLine().split(" ");
                searchByName(values[2]);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } else if(command.contains("Exit")) {
            exit();
        } else {
            System.out.println("Invalid command. Please try again:\n");
        }
    }

    private void exit () {
        System.out.println("Program closed");
    }

    @Override
    public String searchDepartment (String department) {

        List<Employee> empByDepartment = new ArrayList<>();
        for (Employee emp : csvToList()) {
            if(emp.getDepartment().equals(department)) {
                empByDepartment.add(emp);
            }
        }
        return empByDepartment.toString();
    }

    @Override
    public void searchEmployee (int id) {
        Employee emp = csvToList().get(id);
        System.out.println(emp.toString());
    }

    @Override
    public List<Employee> searchByName (String name) {
        List<Employee> empByName = new ArrayList<>();
        for(Employee emp : csvToList()) {
            if(emp.getName().contains(name)) {
                empByName.add(emp);
            }
        }
        System.out.println(empByName);
        return empByName;
    }

    public void addEmployee() {
        Scanner scanner = new Scanner(System.in);
        String[] values = scanner.nextLine().split(",");
            try {
                if(values.length >=4) {
                    Employee emp = new Employee(values[0], values[1], values[2],
                            Double.parseDouble(values[3]));
                    employees.add(emp);
                    CSVWriter writer = new CSVWriter();
                    writer.write(employees, filePath);
                    System.out.println("Successfully added employee.");
                } else {
                    System.out.println("Invalid length.");
                }

            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

    }


    @Override
    public void fireEmployee (int id) {
        List<Serializable> empList = Collections.singletonList((Serializable) csvToList());
        Employee emp = (Employee) empList.get(id);
        emp.setEndDate(LocalDate.now());
        CSVWriter writer = new CSVWriter();
        writer.write(empList,filePath);
    }

    @Override
    public void editEmployee (int id) {
        Scanner scanner = new Scanner(System.in);
        for (Object employee : employees) {
            if(employee instanceof Employee) {
                if(((Employee) employee).getId() == id) {
                    System.out.println("Employee found: \n" +
                            employee.toString() + "\n");
                    System.out.println("Set name: (current one {" +((Employee) employee).getName() +"}");
                    ((Employee) employee).setName(scanner.nextLine());
                    System.out.println("Set department: (current one {" +((Employee) employee).getDepartment() + "})");
                    ((Employee) employee).setDepartment(scanner.nextLine());
                    System.out.println("Set salary: (current one {" + ((Employee) employee).getSalary() +"})");
                    ((Employee) employee).setSalary(Double.parseDouble(scanner.nextLine()));
                    employees.set(id, (Employee) employee);
                    saveData(employees);
                    break;
                }
            }
        }

    }

    public List<Employee> csvToList() {
        CSVReader reader = new CSVReader();
        return (List<Employee>) reader.read(filePath);
    }

    public void saveData(List<Serializable> items){
        CSVWriter writer = new CSVWriter();
        writer.write(items, filePath);
    }

    public String printEmpInfo(Employee employee) {
       return employee.toString();
    }

}
