package services;


import java.io.*;
import java.util.List;

public class CSVWriter implements Writer{
    @Override
    public void write (List<Serializable> employees, String filePath) {
        try(FileWriter writer = new FileWriter(filePath, true)) {

            for (Serializable employee: employees) {
                writer.write(employee.toString() + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
