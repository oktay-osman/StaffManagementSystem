package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
    public static int serialVersionUID = 1;
    private final int id;
    private String name;
    private final LocalDate startDate;
    private LocalDate endDate;
    private String department;
    private String role;
    private double salary;

    public Employee(String name, String department, String role, double salary) {
        this.id = serialVersionUID++;
        this.name = name;
        this.startDate = LocalDate.now();
        this.endDate = null;
        this.department = department;
        this.role = role;
        this.salary = salary;
    }

    public Employee(int id, String name, LocalDate startDate, LocalDate endDate,
                    String department ,String role, double salary) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.department = department;
        this.role = role;
        this.salary = salary;
    }

    public LocalDate getStartDate () {
        return startDate;
    }

    public LocalDate getEndDate () {
        return endDate;
    }

    public void setEndDate (LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getId () {
        return id;
    }
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getDepartment () {
        return department;
    }

    public void setDepartment (String department) {
        this.department = department;
    }

    public String getRole () {
        return role;
    }

    public void setRole (String role) {
        this.role = role;
    }

    public double getSalary () {
        return salary;
    }

    public void setSalary (double salary) {
        this.salary = salary;
    }

    @Override
    public String toString () {
        return id + "," + name + "," + startDate + "," + endDate
                + "," + department + "," + role + "," + salary;
    }

}
