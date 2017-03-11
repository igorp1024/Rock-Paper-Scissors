package com.wowapp;

/**
 * Represents round outcome from engine's perspective.
 *
 * @author ip
 */
public enum Outcome {
    WIN, DRAW, LOSS;

    public static Outcome check(Item computersChoice, Item playersChoice) {
        return computersChoice.drawFor(playersChoice)
               ? Outcome.DRAW
               : computersChoice.beats(playersChoice) ? Outcome.WIN : Outcome.LOSS;
    }
}
