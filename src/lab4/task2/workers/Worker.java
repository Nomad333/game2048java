package lab4.task2.workers;

import lab4.task2.parts.*;

public class Worker implements IWorker {

    @Override
    public void work(House house) {

        int built = house.getBuiltParts();

        switch (built) {
            case 0 -> house.addPart(new Basement());
            case 1, 2, 3, 4 -> house.addPart(new Wall());
            case 5 -> house.addPart(new Door());
            case 6, 7, 8, 9 -> house.addPart(new Window());
            case 10 -> house.addPart(new Roof());
            default -> throw new RuntimeException();
        }

        System.out.println("Worker built part #" + house.getBuiltParts());
    }
}
