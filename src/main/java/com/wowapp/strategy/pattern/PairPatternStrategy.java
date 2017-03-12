package com.wowapp.strategy.pattern;

import com.wowapp.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Player's pattern detection based strategy. Tries to find matching pattern in past player's and
 * computer's moves and play respectively in return.
 *
 * @author ip
 */
public class PairPatternStrategy extends AbstractPatternStrategy {

    private static char[] alphabet =
        "0123456789abcdefghijklmnopqrstuvwxyz"
            .toCharArray(); // This alphabet supports up to 6 items

    private static Map<Pair, Character> pairCodes = new HashMap<>(TOTAL_ITEMS * TOTAL_ITEMS);
    private static Map<Character, Pair> pairsByCode = new HashMap<>(TOTAL_ITEMS * TOTAL_ITEMS);

    static {
        // Check the alphabet length, it should be not shorter than (items length)²
        double maximumItemSupported = Math.floor(Math.sqrt(alphabet.length));
        if (maximumItemSupported < TOTAL_ITEMS) {
            throw new IllegalArgumentException(
                String.format(
                    "Internal alphabet is too short to encode all possible items combinations."
                    + " %d required, but it's length is %d",
                    TOTAL_ITEMS * TOTAL_ITEMS,
                    alphabet.length
                )
            );
        }

        // Building a map with all possible moves variations encoded as alphabet symbols.
        // For instance, Rock/Rock=>0, Rock/Paper=>1, Rock/Scissors=>2, ...
        Item[] items = Item.values();
        for (int playersItemIdx = 0; playersItemIdx < TOTAL_ITEMS; playersItemIdx++) {
            for (int computersItemIdx = 0; computersItemIdx < TOTAL_ITEMS; computersItemIdx++) {
                pairCodes.put(
                    new Pair(items[playersItemIdx], items[computersItemIdx]),
                    alphabet[playersItemIdx * TOTAL_ITEMS + computersItemIdx]
                );
            }
        }

        // Translating this to reverse map, 0=>Rock/Rock, 1=>Rock/Paper, 2=>Rock/Scissors, ...
        pairsByCode =
            pairCodes.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    protected StringBuilder buildHistory(List<Item> allPlayersMoves, List<Item> allComputersMoves) {

        Iterator<Item>
            apmIterator = allPlayersMoves.iterator(),
            acmIterator = allComputersMoves.iterator();

        StringBuilder dna = new StringBuilder(allPlayersMoves.size());

        while (apmIterator.hasNext() && acmIterator.hasNext()) {
            dna.append(
                pairCodes.get(
                    new Pair(apmIterator.next(), acmIterator.next())
                )
            );
        }
        return dna;
    }

    protected Item guessUpcomingPlayersItem(StringBuilder history,
                                            int chainLength,
                                            int firstIndexOf) {

        // Guess the next PLAYER_ITEM<=>COMPUTER_ITEM pair
        Pair nextGuessedPair = pairsByCode.get(history.charAt(firstIndexOf + chainLength));

        // Get corresponding player's move
        return nextGuessedPair.getPlayersItem();
    }

    /*package*/ static class Pair {

        private Item playersItem;
        private Item computersResponse;

        public Pair(Item playersItem, Item computersResponse) {
            this.playersItem = playersItem;
            this.computersResponse = computersResponse;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Pair pair = (Pair) o;

            if (playersItem != pair.playersItem) {
                return false;
            }
            return computersResponse == pair.computersResponse;
        }

        @Override
        public int hashCode() {
            int result = playersItem != null ? playersItem.hashCode() : 0;
            result = 31 * result + (computersResponse != null ? computersResponse.hashCode() : 0);
            return result;
        }

        public Item getPlayersItem() {
            return playersItem;
        }
    }
}
