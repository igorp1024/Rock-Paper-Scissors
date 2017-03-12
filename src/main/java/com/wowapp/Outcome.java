package com.wowapp;

/**
 * Represents round outcome from engine's perspective.
 *
 * @author ip
 */
public enum Outcome {
    WIN, DRAW, LOSS;

    /**
     * Builds the result of computer's vs player's items check.
     *
     * @param computersChoice {@link Item} as a computer's choice.
     * @param playersChoice   {@link Item} as a player's choice.
     * @return outcome from computer's standpoint.
     */
    public static Outcome check(Item computersChoice, Item playersChoice) {
        return computersChoice.drawFor(playersChoice)
               ? Outcome.DRAW
               : computersChoice.beats(playersChoice) ? Outcome.WIN : Outcome.LOSS;
    }
}
