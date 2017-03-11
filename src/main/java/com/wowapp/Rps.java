package com.wowapp;

import java.util.Scanner;

/**
 * @author ip
 */
public class Rps {

    private void go() {

        Scanner sc = new Scanner(System.in);

        Engine engine = new Engine();
        ScoreKeeper scoreKeeper = new ScoreKeeper();

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

                System.out.println("\nBad input.");
                printLegend();

            } else {

                // Play the item
                Item computersChoice = engine.makeChoice(lastPlayersChoice, lastOutcome);

                // Keep the player's choice and round outcome for further analysis
                lastPlayersChoice = playersChoice;
                lastOutcome = Outcome.check(computersChoice, playersChoice);

                // Keep totals
                scoreKeeper.trackOutcome(lastOutcome);

                // Print totals and outcome
                printOutcome(scoreKeeper, computersChoice, playersChoice);
            }
        }
    }

    private void printLegend() {
        System.out.println("Enter 'R' to play Rock, 'P' to play Paper, 'S' to play Scissors, "
                           + " (press Enter to exit).");
        System.out.println("Scores legend: [COMPUTER_WINS:PLAYER_WINS(DRAWS)].");
    }

    private void printOutcome(ScoreKeeper scoreKeeper, Item computersChoice, Item playersChoice) {

        String scores =
            String.format("%d:%d(%d)",
                          scoreKeeper.getWins(), scoreKeeper.getLosses(), scoreKeeper.getDraws());

        String message;
        switch (scoreKeeper.getLastOutcome()) {
            case DRAW:
                message = String.format("Draw. %s vs %s.", computersChoice, playersChoice);
                break;
            case WIN:
                message = String.format("My %s beats your %s.", computersChoice, playersChoice);
                break;
            case LOSS:
                message = String.format("Your %s beats mine %s.", playersChoice, computersChoice);
                break;
            default:
                throw new IllegalStateException("Abnormal round outcome");
        }
        System.out.println(String.format("[%s] %s", scores, message));
    }

    public static void main(String[] args) {
        new Rps().go();
    }
}
