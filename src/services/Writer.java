package services;

import java.io.Serializable;
import java.util.List;

public interface Writer {
    public void write(List<Serializable> items, String filePath);
}
