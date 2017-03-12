package com.wowapp.strategy.pattern;

import com.wowapp.Item;
import com.wowapp.strategy.IStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract pattern finding strategy which seeks for patterns in player's moves and tries to predict
 * the next move.
 *
 * @author ip
 */
public abstract class AbstractPatternStrategy implements IStrategy {

    private static final int
        MIN_CHAIN_LENGTH = 3, // Minimal pattern length
        MAX_CHAIN_LENGTH = 10; // Maximal patterns length

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

                // Find a char from history which is supposed to appear in this repeated pattern
                char historyChar = history.charAt(firstIndexOf + chainLength);

                // Convert guessed char to guessed item
                Item guessedPlayersItem = convertHistoryCharToItem(historyChar);

                return winningChoice.get(guessedPlayersItem);
            }
        }

        // No patterns used by player found so far
        return Item.getRandom();
    }

    /**
     * Builds the history of previous rounds depending on player's and computer's moves records.
     *
     * @param allPlayersMoves   list of player's moves.
     * @param allComputersMoves list of computer's moves.
     * @return <tt>StringBuilder</tt> containing history records encoded as chars (see each
     * implementation for the details).
     */
    protected abstract StringBuilder buildHistory(List<Item> allPlayersMoves,
                                                  List<Item> allComputersMoves);

    /**
     * Converts char from history to game {@link Item}.
     *
     * @param historyChar char from history chain.
     * @return {@link Item} for the specified char.
     */
    protected abstract Item convertHistoryCharToItem(char historyChar);

}