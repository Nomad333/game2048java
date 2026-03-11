package lab5.task3;


import lab5.task3.worker.CorpWorker;
import lab5.task3.worker.Worker;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Corporation implements Loader<List<Worker>>, WorkersManager {
    private List<Worker> workers = new ArrayList<>();

    public Corporation() {
    }

    public Corporation(List<Worker> workers) {
        this.workers = workers;
    }

    List<Worker> searchWorkerBy(Predicate<Worker> workerPredicate) {
        return workers.stream().filter(workerPredicate).toList();
    }

    void printWorkersBy(Predicate<Worker> workerPredicate) {
        var result = searchWorkerBy(workerPredicate);
        if (result.isEmpty()) {
            System.out.println("No workers found");
        } else {
            result.forEach(System.out::println);
        }
    }

    @Override
    public void save(String filePath) {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (var worker : workers) {
                writer.write(worker.toString());
                writer.newLine();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void load(String filePath) {
        workers.clear();

        Pattern pattern = Pattern.compile("\\[name=(.*?),surname=(.*?),birthDate=(.*?)]");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) {
                    String name = matcher.group(1);
                    String surname = matcher.group(2);
                    LocalDate birthDate = LocalDate.parse(matcher.group(3));

                    workers.add(new CorpWorker(name, surname, birthDate));
                }
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    @Override
    public void editWorker(Worker oldWorker, Worker newWorker) {
        workers.replaceAll(w -> w.equals(oldWorker) ? newWorker : w);
    }

    @Override
    public void removeWorker(Worker worker) {
        workers.remove(worker);
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
