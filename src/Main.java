import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //system objects
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();

        //game variables
        String[] enemies = {"Skeleton", "Zombie", "Barbarian", "Orc", "Goblin"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        //player variables
        int health = 100;
        int attackDamage = 50;
        int numHealthPots = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 30; //percentage 30%

        boolean running = true;

        System.out.println("Welcome to the Dungeon!");

        GAME: //label
        while (running) {
            System.out.println("-----------------------------------------------------------------------");
            int enemyHealth = rand.nextInt(maxEnemyHealth); //set enemy health between 0 to max (75)
            String enemy = enemies[rand.nextInt(enemies.length)]; //randomize the enemy encounter
            System.out.println("\t# " + enemy + " appeared! #\n");

            while(enemyHealth > 0) {
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink Health Pot");
                System.out.println("\t3. Run!");

                String input = keyboard.nextLine();

                if(input.equalsIgnoreCase("1")) {
                    int damageDealt = rand.nextInt(attackDamage);
                    int damageTake = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    health -= damageTake;

                    System.out.println("\t> You striked the " + enemy + " for " + damageDealt + " damage!");
                    System.out.println("\t> You recieved " + damageTake + " in retaliation!");
                    if (health < 1) {
                        System.out.println("\t You have died!");
                        break;
                    }
                } else if (input.equalsIgnoreCase("2")) {
                    if(numHealthPots > 0) {
                        health += healthPotionHealAmount;
                        numHealthPots --;
                        System.out.println("\t> You dranked a health pot, healing yourself for " + healthPotionHealAmount + "."
                        + "\n\t You have " + numHealthPots + " health pots left.\n");
                    } else {
                        System.out.println("\t> You have no health pots left! Defeat enemies for a chance to drop " +
                                "more health pots!");
                    }
                } else if (input.equalsIgnoreCase("3")) {
                    System.out.println("\tYou ran away from " + enemy + "!");
                    continue GAME;
                } else {
                    System.out.println("\tInvalid command!");
                }
            }
            if(health < 1) {
                System.out.println("Your corpse is rotting in the dungeon!");
                break;
            }
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println(" # You have " + health + " HP left.");
            attackDamage++;
            if (rand.nextInt(100) < healthPotionDropChance) {
                numHealthPots++;
                System.out.println(" # The " + enemy + " dropped a health pot! # ");
                System.out.println(" # You now have " + numHealthPots + " health pots. # ");
            }
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("What wou you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            String input = keyboard.nextLine();

            while (!input.equalsIgnoreCase("1") && !input.equalsIgnoreCase("2")) {
                System.out.println("Invalid command!");
                input = keyboard.nextLine();
            }

            if (input.equalsIgnoreCase("1")) {
                System.out.println("You continue on your adventure!");
            } else if (input.equalsIgnoreCase("2")) {
                System.out.println("Cowards will survive...you slowly exit the dungeon!");
                break;
            }
        }
        System.out.println("######################");
        System.out.println("##THAKNS FOR PLAYING##");
        System.out.println("######################");
    }
}