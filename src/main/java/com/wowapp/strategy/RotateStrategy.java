package com.wowapp.strategy;

import com.wowapp.Item;

import java.util.List;

/**
 * This strategy tries to win by choosing the item which beats the last player's choice.
 * ROCK -> PAPER, PAPER -> SCISSORS, SCISSORS -> ROCK.
 *
 * @author ip
 */
public class RotateStrategy implements IStrategy {

    @Override
    public Item guessTheItem(List<Item> allPlayersMoves, List<Item> allComputersMoves) {

        Item lastPlayersChoice = allPlayersMoves.get(allPlayersMoves.size() - 1);

        // Rotate previous player's move by one (to the item that beats it)
        switch (lastPlayersChoice) {
            case ROCK:
                return Item.PAPER;
            case PAPER:
                return Item.SCISSORS;
            case SCISSORS:
                return Item.ROCK;
            default:
                throw new IllegalStateException("Unsupported item specified");
        }
    }
}
