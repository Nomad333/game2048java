package lab5.task3;

import lab5.task3.worker.Worker;

public interface WorkersManager {
    void addWorker(Worker worker);

    void editWorker(Worker oldWorker, Worker newWorker);

    void removeWorker(Worker worker);
}
