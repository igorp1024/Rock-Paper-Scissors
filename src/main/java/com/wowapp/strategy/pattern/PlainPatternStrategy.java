package com.wowapp.strategy.pattern;

import com.wowapp.Item;

import java.util.Iterator;
import java.util.List;

/**
 * Player's pattern detection based strategy. Tries to find matching pattern in past player's only
 * moves and play respectively in return.
 *
 * @author ip
 */
public class PlainPatternStrategy extends AbstractPatternStrategy {

    @Override
    protected StringBuilder buildHistory(List<Item> allPlayersMoves, List<Item> allComputersMoves) {
        Iterator<Item> playersMovesIterator = allPlayersMoves.iterator();

        StringBuilder history = new StringBuilder(allPlayersMoves.size());
        while (playersMovesIterator.hasNext()) {
            history.append(playersMovesIterator.next().getValue());
        }

        return history;
    }

    @Override
    protected Item guessUpcomingPlayersItem(StringBuilder history,
                                            int chainLength,
                                            int firstIndexOf) {
        // Guess the next player's item
        char playersItemAsChar = history.charAt(firstIndexOf + chainLength);
        String playersItemAsString = Character.toString(playersItemAsChar);

        return Item.findValue(playersItemAsString);
    }
}
