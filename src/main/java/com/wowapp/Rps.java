package com.wowapp;

import java.util.Scanner;

/**
 * @author ip
 */
public class Rps {

    private void go() {

        Scanner sc = new Scanner(System.in);

        printLegend();
        while (true) {

            // Read the player's input
            String nl = sc.nextLine();
            if ("".equals(nl)) {
                System.out.println("Exiting ...");
                return;
            }

            // Check the input
            Item playersChoice = Item.findValue(nl);
            if (playersChoice == null) {
                System.out.print("Bad input. ");
                printLegend();
            } else {
                // Play the item
                Item computerChoice = chooseTheItemToPlay();
                printResults(computerChoice, playersChoice);
            }
        }
    }

    private Item chooseTheItemToPlay() {
        return Item.ROCK;
    }

    private void printLegend() {
        System.out.println("Enter 'R' to play Rock, 'P' to play Paper, 'S' to play Scissors."
                           + " Press Enter to exit.");
    }

    private void printResults(Item computersChoice, Item playersChoice) {

        if (computersChoice.drawFor(playersChoice)) {
            System.out.println(
                String.format(
                    "My %s vs your %s. Draw.", computersChoice, playersChoice
                )
            );
        } else if (computersChoice.beats(playersChoice)) {
            System.out.println(
                String.format("My %s beats your %s. ", computersChoice, playersChoice)
            );
        } else {
            System.out.println(
                String.format("Your %s beats mine %s. ", playersChoice, computersChoice)
            );
        }
    }

    public static void main(String[] args) {
        new Rps().go();
    }
}
