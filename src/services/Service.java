package services;

import java.io.Serializable;
import java.util.List;

public interface Service {
    public Writer writer = null;
    public Reader reader = null;

    public List<? extends Serializable> getRecords ();

    public void writeRecords(List<Serializable> list,String filePath);
}
