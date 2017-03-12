package com.wowapp.strategy;

import com.wowapp.Item;

import java.util.List;

/**
 * Weighted score based strategy.
 *
 * @author ip
 */
public class WeightedStrategy implements IStrategy {

    @Override
    public Item guessTheItem(List<Item> allPlayersMoves, List<Item> allComputersMoves) {

        double rockWeight = 0, paperWeight = 0, scissorsWeight = 0;

        // Not very efficient (weights are being recalculated each round), but strategy is
        // stateless by design and will recheck the entire state (aka playedItems) after being
        // changed and becoming effective again
        for (Item nextItem : allPlayersMoves) {

            // Degrade weights slightly in order the next iteration they will not be
            // self-compensated
            rockWeight *= 0.95;
            paperWeight *= 0.95;
            scissorsWeight *= 0.95;

            switch (nextItem) {
                case ROCK:
                    paperWeight += 0.1;
                    scissorsWeight -= 0.1;
                    break;
                case PAPER:
                    scissorsWeight += 0.1;
                    rockWeight -= 0.1;
                    break;
                case SCISSORS:
                    rockWeight += 0.1;
                    paperWeight -= 0.1;
                    break;
            }
        }

        if (rockWeight > paperWeight && rockWeight > scissorsWeight) {
            return Item.ROCK;
        } else if (paperWeight > scissorsWeight) {
            return Item.PAPER;
        } else {
            return Item.SCISSORS;
        }
    }
}
