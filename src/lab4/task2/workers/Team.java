package lab4.task2.workers;

import lab4.task2.parts.House;

import java.util.List;

public class Team implements IWorker {
    private List<IWorker> workers;

    public Team(List<IWorker> workers) {
        this.workers = workers;
    }

    public void work(House house) {
        while (house.getBuiltParts() < 11) {
            for (IWorker worker : workers) {
                worker.work(house);

                if (house.getBuiltParts() >= 11)
                    break;
            }
        }

        System.out.println("House is completed!");
    }
}
