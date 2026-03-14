package task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StreamLogger<T> {
    private final Path logPath;

    public StreamLogger(Path logPath) {
        this.logPath = logPath;
    }

    public void append(T log) {
        try {
            Files.writeString(logPath, log.toString() + "\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        try {
            Files.deleteIfExists(logPath);
            Files.createFile(logPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
