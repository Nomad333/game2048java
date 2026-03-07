package lab4.task2;

import lab4.task2.parts.House;
import lab4.task2.workers.Team;
import lab4.task2.workers.TeamLeader;
import lab4.task2.workers.Worker;

import java.util.List;

public class Main {
    /*
        Реалізувати програму "Будівництво будинку" з використанням:
    класів:
    - House (Будинок), Basement (Фундамент), Walls (Стіни), Door (Двері), Window (Вікно), Roof (Дах);
    - Team (Бригада будівельників), Worker (Будівельник), TeamLeader (Бригадир).
    та інтерфейсів
    - IWorker, IPart.

    Всі частини будинку повинні реалізувати інтерфейс IPart (Частина будинку),
    для робітників і бригадира надається базовий інтерфейс IWorker (Робочий).
    Бригада будівельників (Team) будує будинок (House).
    Будинок складається з фундаменту (Basement), стін (Wall), вікон (Window),
    дверей (Door), дахи (Part). Згідно з проектом, в будинку повинно бути 1
    фундамент, 4 стіни, 1 двері, 4 вікна і 1 дах. Бригада починає роботу,
    і будівельники послідовно "будують" будинок, починаючи з фундаменту.
    Кожен будівельник не знає заздалегідь, на чому завершився попередній
    етап будівництва, тому він "перевіряє", що вже побудовано і продовжує роботу.
    Якщо в гру вступає бригадир (TeamLeader), він не будує, а формує звіт, що вже
    побудовано і яка частина роботи виконана. В кінцевому підсумку на консоль
    виводиться повідомлення, що будівництво будинку завершено.

     */
    public static void main(String[] args) {
        House house = new House();

        Team team = new Team(
                List.of(
                        new Worker(),
                        new Worker(),
                        new TeamLeader()
                )
        );

        team.work(house);
        new TeamLeader().work(house);
    }
}
