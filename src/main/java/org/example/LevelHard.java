package org.example;

import org.apache.commons.lang.time.StopWatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LevelHard {

    static String [][] tableOfWords = new String[2][8];
    static  boolean [][] showOrNot = new boolean[2][8];
    static Scanner scanner = new Scanner(System.in);


    public static void game(boolean[][] showOrNot, String[][] x) {
        int leftMatches = 16;
        int gestChances=15;


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();


        while (leftMatches > 0) {

            System.out.println("Level: hard");
            System.out.println("Guess chances: "+gestChances);
            System.out.println();
            display(showOrNot, x);
            int g1x = 0, g1y=0, g2x=0, g2y=0;
            System.out.println("Coordinates first word: ");
            String g1 = scanner.next();
            if (g1.contains("A")) {
                g1x = Integer.valueOf(0);
                g1y = Integer.valueOf(g1.substring(1, 2))-1;
                showOrNot[g1x][g1y] = true;
                display(showOrNot, x);
            }
            else if(g1.contains("B")){
                g1x = Integer.valueOf(1);
                g1y = Integer.valueOf(g1.substring(1,2)) - 1;
                showOrNot[g1x][g1y] = true;
                display(showOrNot, x);
            }
            System.out.println("Coordinates second word: ");
            String g2 = scanner.next();
            if (g2.contains("A")) {
                g2x = Integer.valueOf(0);
                g2y = Integer.valueOf(g2.substring(1, 2)) - 1;
                System.out.println(x[g2x][g2y]);

            }
            if(g2.contains("B")){
                g2x = Integer.valueOf(1);
                g2y = Integer.valueOf(g2.substring(1, 2)) - 1;
                System.out.println(x[g2x][g2y]);
            }
            if (x[g1x][g1y].equals(x[g2x][g2y])) {
                showOrNot[g2x][g2y] = true;
                System.out.println("You found a match");
                leftMatches -= 2;
            }
            else{
                showOrNot[g1x][g1y]=false;
                System.out.println("You don't have a mach!");

            }
            System.out.println();
            gestChances--;
            if(gestChances==0&&leftMatches == 0){
                stopWatch.stop();
                System.out.println("You win");
                System.out.println("You solved the memory game after "+(10-gestChances)+" chances. It took you "+stopWatch.getTime() + " milliseconds");
                break;
            }



            if (gestChances==0){
                display(showOrNot, x);
                stopWatch.stop();
                System.out.println("Game Over!");
                System.out.println("You dind't solved the memory game after "+(10-gestChances)+" chances. It took you "+stopWatch.getTime() + " milliseconds");
                break;
            }

            if(leftMatches == 0){
                display(showOrNot,x);
                stopWatch.stop();
                System.out.println("You win");
                System.out.println("You solved the memory game after "+(10-gestChances)+" chances. It took you "+stopWatch.getTime() + " milliseconds");
                break;
            }
        }


    }

    public static void setup() throws FileNotFoundException {
        for (int i = 0; i < 2; i++) {
            for (int a = 0; a < 8; a++) {
                showOrNot[i][a]=false; //false because is shows "X"
            }
        }
        tableOfWords = shuffle(); //Shuffle cards
    }


    public static void display(boolean [][] showOrNot, String [][] x){
        System.out.println("   1 2 3 4 5 6 7 8");
        for (int i = 0; i < 2; i++) {
            System.out.print(" " + (Character.toString(65+i))+" ");
            for (int a = 0; a < 8; a++) {
                if (showOrNot[i][a]) {
                    System.out.print(x[i][a]);
                    System.out.print(" ");
                }
                else
                    System.out.print("X ");
            }
            System.out.println();
        }
        System.out.println();


    }

    public static String [][] shuffle() throws FileNotFoundException {

        String x[][]=new String[2][8];
        int r=0;


        File file = new File("src/main/resources/files/Words.txt");
        Scanner scann = new Scanner(file);
        ArrayList<String> words = new ArrayList<String>();
        while (scann.hasNextLine()) {
            words.add(scann.nextLine());
        }
        Collections.shuffle(words); //shuffle words in ArrayList
        ArrayList<String> wordList = new ArrayList<String>();
        wordList.add(words.get(0));
        wordList.add(words.get(1));
        wordList.add(words.get(2));
        wordList.add(words.get(3));
        wordList.add(words.get(4));
        wordList.add(words.get(5));
        wordList.add(words.get(6));
        wordList.add(words.get(7));//adding four shuffled Strings
        wordList.addAll(wordList);
        Collections.shuffle(wordList);
        String[] wordsArray = wordList.toArray(new String[wordList.size()]);


        for (int i = 0; i < 2; i++) {// put values in x here
            for (int j = 0; j < 8; j++) {
                x[i][j] = wordsArray[r];
                r += 1;
            }
        }
        return x;
    }
}
