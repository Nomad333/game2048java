package lab6.task1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class UsersManager {
    public Set<User> users = new HashSet<>();

    public UsersManager() {
    }

    public UsersManager(Collection<User> users) {
        this.users = new HashSet<>(users);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public boolean isUserExists(User user) {
        return users.contains(user);
    }

    public boolean isUserExists(Predicate<User> predicate) {
        return users.stream().anyMatch(predicate);
    }

    public void removeUser(Predicate<User> predicate) {
        users.stream().filter(predicate).toList().forEach(users::remove);
    }

    public void changeLogin(User user, String newLogin) {
        users.stream().filter(u -> u.equals(user)).forEach(u -> u.setLogin(newLogin));
    }

    public void changeLogin(Predicate<User> predicate, String newLogin) {
        users.stream().filter(predicate).forEach(u -> u.setLogin(newLogin));
    }

    public void changePassword(User user, String newPassword) {
        users.stream().filter(u -> u.equals(user)).forEach(u -> u.setPassword(newPassword));
    }

    public void changePassword(Predicate<User> predicate, String newPassword) {
        users.stream().filter(predicate).forEach(u -> u.setPassword(newPassword));
    }
}
