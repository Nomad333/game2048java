package lab4.task2.workers;

import lab4.task2.parts.House;
import lab4.task2.parts.IPart;

public class TeamLeader implements IWorker {
    @Override
    public void work(House house) {

        System.out.println("Report:");

        for (IPart part : house.getParts()) {
            System.out.println("-" + part.getName());
        }

        System.out.println("Built:" + "%.0f%%".formatted((house.getBuiltParts() / 11.) * 100));
    }
}
