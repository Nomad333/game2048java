package lab1.task2;

public class GameState {
    private int health;
    private int enemyScore;
    private int userScore;

    public GameState() {
    }

    public GameState(int health, int enemyScore, int userScore) {
        this.health = health;
        this.enemyScore = enemyScore;
        this.userScore = userScore;
    }

    public void fillUserScore(int level) {
        int k = (level == 0 ? 5 : 10);
        userScore += health * k;
    }

    public void fillEnemyScore(int startHealthUser, int level) {
        int k = (level == 0 ? 5 : 10);
        enemyScore += startHealthUser * k;
    }

    public void reset() {
        health = 0;
        enemyScore = 0;
        userScore = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEnemyScore() {
        return enemyScore;
    }

    public int getUserScore() {
        return userScore;
    }

    @Override
    public String toString() {
        return "Пiдсумок: " +
                "Життiв=" + health +
                ", Очки комп'ютера=" + enemyScore +
                ", Очки користувача=" + userScore;
    }
}
