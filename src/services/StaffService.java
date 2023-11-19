package services;


import java.io.Serializable;
import java.util.List;

public class StaffService implements Service{
    public static CSVReader reader;
    public static CSVWriter writer;

    public StaffService (CSVReader reader, CSVWriter writer) {
        StaffService.reader = reader;
        StaffService.writer = writer;
    }
//    @Override
//    public static List<? extends Serializable> getRecords () {
//        return reader.read("resources/employees_info.csv");
//    }

    @Override
    public List<? extends Serializable>
    getRecords () {
        return reader.read("resources/employees_info.csv");
    }

    public void writeRecords(List<Serializable> list, String filePath) {
        writer.write(list, filePath);
    }

}
