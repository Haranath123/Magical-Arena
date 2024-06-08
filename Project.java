import java.util.Random;

public class Project {

    public static void main(String[] args) {
        Player playerA = new Player(50, 5, 10);
        Player playerB = new Player(100, 10, 5);

        MagicalArena arena = new MagicalArena(playerA, playerB);
        Player winner = arena.fight();

        System.out.println("The winner is " + (winner == playerA ? "Player A" : "Player B"));
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

class MagicalArena {
    private Player playerA;
    private Player playerB;

    public MagicalArena(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public Player fight() {
        while (playerA.isAlive() && playerB.isAlive()) {
            if (playerA.getHealth() <= playerB.getHealth()) {
                executeRound(playerA, playerB);
                if (playerB.isAlive()) {
                    executeRound(playerB, playerA);
                }
            } else {
                executeRound(playerB, playerA);
                if (playerA.isAlive()) {
                    executeRound(playerA, playerB);
                }
            }
        }
        return playerA.isAlive() ? playerA : playerB;
    }

    private void executeRound(Player attacker, Player defender) {
        int attackDice = attacker.rollAttackDice();
        int defenseDice = defender.rollDefenseDice();

        int attackDamage = attacker.calculateAttackDamage(attackDice);
        int defenseStrength = defender.calculateDefenseStrength(defenseDice);

        int damage = attackDamage - defenseStrength;
        if (damage > 0) {
            defender.reduceHealth(damage);
        }
    }
}