package com.wowapp.strategy.pattern;

import com.wowapp.Item;
import com.wowapp.strategy.IStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ip
 */
public abstract class AbstractPatternStrategy implements IStrategy {

    private static final int
        MIN_CHAIN_LENGTH = 3,
        MAX_CHAIN_LENGTH = 10;

    protected static final int TOTAL_ITEMS = Item.values().length;

    private static Map<Item, Item> winningChoice = new HashMap<>(TOTAL_ITEMS);

    static {

        Item[] items = Item.values();

        // Building winning choices map
        for (int i = 0; i < TOTAL_ITEMS - 1; i++) {
            winningChoice.put(items[i], items[i + 1]);
        }
        winningChoice.put(items[items.length - 1], items[0]);
    }

    @Override
    public Item guessTheItem(List<Item> allPlayersMoves, List<Item> allComputersMoves) {

        if (allPlayersMoves.size() < MIN_CHAIN_LENGTH) {
            return Item.getRandom();
        }

        // Not very efficient (recalculations are made each round), but implemented this way for
        // the sake of making strategy stateless
        StringBuilder history = buildHistory(allPlayersMoves, allComputersMoves);

        // Now we have built our rounds history chain - PLAYER_ITEM<=>COMPUTER_ITEM.
        // Next step is to find the repeating blocks, from bigger (MAX) to smaller one (MIN).

        for (int chainLength = MAX_CHAIN_LENGTH; chainLength >= MIN_CHAIN_LENGTH; chainLength--) {

            String nextPattern = history.substring(history.length() - chainLength);
            int lastIndexOf = history.lastIndexOf(nextPattern);
            int firstIndexOf = history.indexOf(nextPattern);

            boolean thereIsAMatch = lastIndexOf != -1;
            boolean thisIsNotTheSamePattern = firstIndexOf != lastIndexOf;

            if (thereIsAMatch && thisIsNotTheSamePattern) {

                Item guessedPlayersItem =
                    guessUpcomingPlayersItem(history, chainLength, firstIndexOf);

                return winningChoice.get(guessedPlayersItem);
            }
        }

        // No patterns used by player so far
        return Item.getRandom();
    }

    protected abstract StringBuilder buildHistory(List<Item> allPlayersMoves,
                                                  List<Item> allComputersMoves);

    protected abstract Item guessUpcomingPlayersItem(StringBuilder history,
                                                     int chainLength,
                                                     int firstIndexOf);

}