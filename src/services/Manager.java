package services;

import model.Employee;

import java.util.List;

public interface Manager {
    void addEmployee(); //TODO DONE
    void fireEmployee(int id); //TODO refactor
    void editEmployee(int id); //TODO implement the edit function - in progress
    void execute(String command); //TODO check if this can be done better instead of having the logic in main class
    String searchDepartment(String department); //TODO refactor
    void searchEmployee(int id); //TODO refactor
    List<Employee> searchByName(String name); //TODO refactor
}
