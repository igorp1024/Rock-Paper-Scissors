package com.wowapp.strategy.pattern;

import com.wowapp.Item;

import java.util.Iterator;
import java.util.List;

/**
 * Player's pattern detection based strategy. Tries to find matching pattern in past player's only
 * moves and play respectively in return (unlike {@link PairPatternStrategy} which seeks for
 * repeatable patterns formed by player+computer moves pair).
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
    protected Item convertHistoryCharToItem(char historyChar) {

        // Guess the next player's item
        return Item.findValue(Character.toString(historyChar));
    }
}
