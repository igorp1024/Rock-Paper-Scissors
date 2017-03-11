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
    public Item guessTheItem(List<Item> playedItems) {

        double rockWeight = 0, paperWeigth = 0, scissorsWeight = 0;

        // Not very efficient, but strategy is stateless by design and will recheck the entire state
        // (aka playedItems) after being changed and becoming effective again
        for (Item nextItem : playedItems) {

            rockWeight *= 0.95;
            paperWeigth *= 0.95;
            scissorsWeight *= 0.95;

            switch (nextItem) {
                case ROCK:
                    paperWeigth += 0.1;
                    scissorsWeight -= 0.1;
                    break;
                case PAPER:
                    scissorsWeight += 0.1;
                    rockWeight -= 0.1;
                    break;
                case SCISSORS:
                    rockWeight += 0.1;
                    paperWeigth -= 0.1;
                    break;
            }
        }

        if (rockWeight > paperWeigth && rockWeight > scissorsWeight) {
            return Item.ROCK;
        } else if (paperWeigth > scissorsWeight) {
            return Item.PAPER;
        } else {
            return Item.SCISSORS;
        }
    }
}
