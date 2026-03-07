package lab4.task1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface WritableReadable<T> extends Serializable {

    T readFromFile(String path) throws IOException, ClassNotFoundException;

    void writeToFile(String path) throws IOException;
}
