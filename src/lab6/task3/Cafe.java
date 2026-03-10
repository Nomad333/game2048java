package lab6.task3;

import lab6.task3.pool.TablePool;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Cafe {
    private final int numberOfTables;
    private final Queue<Table> occupiedTables = new ArrayDeque<>();
    private final TablePool pool;
    private Queue<Visitor> queue = new ArrayDeque<>();
    private List<Reservation> reservations = new ArrayList<>();

    public Cafe(int numberOfTables) {
        this.numberOfTables = numberOfTables;
        this.pool = new TablePool(numberOfTables);
    }

    public Cafe(Queue<Visitor> queue, List<Reservation> reservations, TablePool pool, int numberOfTables) {
        this.queue = queue;
        this.reservations = reservations;
        this.pool = pool;
        this.numberOfTables = numberOfTables;
    }

    // TODO: Visitor... visitors
    public void addVisitor(Visitor visitor) {
        var reservation = reservations.stream()
                .filter(r -> r.visitor().equals(visitor))
                .findFirst();

        // Якщо відвідувач має резервування, він отримує зарезервований столик позачергово
        if (reservation.isPresent()) {
            reservation.ifPresent(res -> reservations.remove(res));
            occupiedTables.add(reservation.get().table());
            System.out.println(visitor.name() + " got a reservation table " + reservation.get().table());
            return;
        }

        if (pool.getCurrentPullSize() > 0) {
            Table table = pool.acquire();
            occupiedTables.add(table);
            System.out.println(visitor.name() + " got a table " + table.number());
        } else {
            queue.add(visitor);
            System.out.println(visitor.name() + " is waiting in the queue");
        }
    }


    public void addReservation(Visitor visitor, LocalDate expDate) {
        var table = pool.acquire();  // TODO: ISSUE
        if (table == null) {
            System.out.println("No tables available for reservation");
            return;
        }
        Reservation reservation = new Reservation(table, visitor, expDate);
        reservations.add(reservation);
        System.out.println(reservation.visitor().name() + " reserved a table at " + reservation.expDate());
    }

    public void ReservationCollector() {
        for (var res : reservations) {
            if (res.expDate().isBefore(LocalDate.now())) {
                System.out.println("table:" + res.table() + " reservation expired");
                reservations.remove(res);
            }
        }
    }

    public void freeFirstTableAndCheckQueue() {
        if (occupiedTables.isEmpty()) {
            System.out.println("No occupied tables to free");
            return;
        }
        Table table = occupiedTables.poll();
        pool.release(table);
        System.out.println("Freed table " + table.number());

        // Після звільнення столика, перевіряємо чергу і надаємо столик наступному відвідувачу, якщо він є
        if (!queue.isEmpty()) {
            Visitor nextVisitor = queue.poll();
            addVisitor(nextVisitor);
        }
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public Queue<Table> getOccupiedTables() {
        return occupiedTables;
    }

    public Queue<Visitor> getQueue() {
        return queue;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}