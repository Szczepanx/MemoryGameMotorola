package org.example;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello :)");
        System.out.println("Chose difficulty level('E'-easy,'H'-hard): ");
        String difficulty = scanner.next();
        if (difficulty.equals("E")){
            LevelEasy.setup();
            LevelEasy.game(LevelEasy.showOrNot,LevelEasy.tableOfWords);
        }
        else if (difficulty.equals("H")){
            LevelHard.setup();
            LevelHard.game(LevelHard.showOrNot,LevelHard.tableOfWords);
        }
        else{
            System.out.println("Goodbye :)");
        }
    }
}
