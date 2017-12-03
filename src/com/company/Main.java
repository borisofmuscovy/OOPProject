package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Monster Bob = new Monster("Bob", 15, 10, 10);
        Monster Alice = new Monster("Alice", 15, 12, 11);
        int swings = 0;
        while (Bob.isAlive() && Alice.isAlive()) {
            Bob.hit(Alice);
            Alice.hit(Bob);
            swings++;
            if (swings == 15) {
                System.out.println("Draw.");
                break;
            }
        }

    }
}
