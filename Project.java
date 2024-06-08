import java.util.Random;

public class Project {

    public static void main(String[] args) {
        Player playerA = new Player(50, 5, 10);
        Player playerB = new Player(100, 10, 5);
        
    }
}

class Player {
    private int health;
    private int strength;
    private int attack;
    private Random random;

    public Player(int health, int strength, int attack) {
        this.health = health;
        this.strength = strength;
        this.attack = attack;
        this.random = new Random();
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public int rollAttackDice() {
        return random.nextInt(6) + 1;
    }

    public int rollDefenseDice() {
        return random.nextInt(6) + 1;
    }

    public int calculateAttackDamage(int diceRoll) {
        return this.attack * diceRoll;
    }

    public int calculateDefenseStrength(int diceRoll) {
        return this.strength * diceRoll;
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}