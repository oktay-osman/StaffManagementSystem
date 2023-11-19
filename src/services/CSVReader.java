package services;

import model.Employee;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements Reader{
    @Override
    public List<? extends Serializable> read (String filePath) {
        List<Employee> employees = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(", *");
                LocalDate endDate = null;
                if(values.length != 0) {
                    if(!values[3].equals("null")) {
                        endDate = LocalDate.parse(values[3]);
                    }
                }

                employees.add(new Employee(Integer.parseInt(values[0]),
                    values[1], LocalDate.parse(values[2]), endDate,
                    values[4], values[5], Double.parseDouble(values[6])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}
