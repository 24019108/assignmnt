/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.combatgame;

/**
 *
 * @author MY PC
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// Abstract class for game characters
abstract class Character {
    protected String name;
    protected int health;
    protected int attack;

    public Character(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public abstract void attack(Character opponent);

    public void displayStats() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Attack: " + attack);
    }
}

// Concrete class for Warrior character
class Warrior extends Character {
    public Warrior(String name) {
        super(name, 100, 20);
    }

    @Override
    public void attack(Character opponent) {
        int damage = attack + new Random().nextInt(10); // Add some randomness to attack
        opponent.health -= damage;
        System.out.println(name + " attacks " + opponent.name + " for " + damage + " damage!");
    }
}

// Concrete class for Mage character
class Mage extends Character {
    public Mage(String name) {
        super(name, 80, 30);
    }

    @Override
    public void attack(Character opponent) {
        int damage = attack + new Random().nextInt(15); // Add some randomness to attack
        opponent.health -= damage;
        System.out.println(name + " casts a spell on " + opponent.name + " for " + damage + " damage!");
    }
}


public class Combatgame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Combat Game!");

        // Character creation
        System.out.print("Enter the name for the Warrior: ");
        String warriorName = scanner.nextLine();
        Warrior warrior = new Warrior(warriorName);

        System.out.print("Enter the name for the Mage: ");
        String mageName = scanner.nextLine();
        Mage mage = new Mage(mageName);

        // Combat loop
        Character[] characters = {warrior, mage};
        int turn = 0;
        while (warrior.health > 0 && mage.health > 0) {
            System.out.println("\n Turn " + (turn + 1) + " ");
            characters[turn % 2].displayStats();
            characters[(turn + 1) % 2].displayStats();
            characters[turn % 2].attack(characters[(turn + 1) % 2]);
            turn++;
        }

        // Game over
        System.out.println("\n Game Over ");
        if (warrior.health <= 0) {
            System.out.println(mage.name + " wins!");
        } else {
            System.out.println(warrior.name + " wins!");
        }
        scanner.close();
    }
}
