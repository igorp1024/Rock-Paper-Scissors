package com.wowapp;

import java.util.Scanner;

/**
 * @author ip
 */
public class Rps {

    private Engine engine = new Engine();

    private void go() {

        Scanner sc = new Scanner(System.in);

        Item lastPlayersChoice = null;
        Outcome lastOutcome = null;

        printLegend();

        while (true) {

            // Read the player's input
            String playersInput = sc.nextLine();
            if ("".equals(playersInput)) {
                System.out.println("Exiting ...");
                return;
            }

            // Check the input
            Item playersChoice = Item.findValue(playersInput);
            if (playersChoice == null) {

                System.out.print("Bad input. ");
                printLegend();

            } else {

                // Play the item
                Item computersChoice = engine.makeChoice(lastPlayersChoice, lastOutcome);

                lastPlayersChoice = playersChoice;
                lastOutcome = Outcome.check(computersChoice, playersChoice);

                System.out.println(formatMessage(lastOutcome, computersChoice, playersChoice));
            }
        }
    }

    private void printLegend() {
        System.out.println("Enter 'R' to play Rock, 'P' to play Paper, 'S' to play Scissors."
                           + " Press Enter to exit.");
    }

    private String formatMessage(Outcome outcome, Item computersChoice, Item playersChoice) {
        switch (outcome) {
            case DRAW:
                return String.format("My %s vs your %s. Draw.", computersChoice, playersChoice);
            case WIN:
                return String.format("My %s beats your %s. ", computersChoice, playersChoice);
            case LOSS:
                return String.format("Your %s beats mine %s. ", playersChoice, computersChoice);
            default:
                throw new IllegalStateException("Abnormal round outcome");
        }
    }

    public static void main(String[] args) {
        new Rps().go();
    }
}
