package com.wowapp.strategy.pattern;

import com.wowapp.Item;
import com.wowapp.strategy.IStrategy;
import com.wowapp.strategy.pattern.PairPatternStrategy.Pair;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.wowapp.Item.PAPER;
import static com.wowapp.Item.ROCK;
import static com.wowapp.Item.SCISSORS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author ip
 */
public class PairPatternStrategyTest {

    @Test
    public void test() {

        IStrategy strategy = new PairPatternStrategy();

        Item[] allPlayersMoves = new Item[]{
            SCISSORS,
            PAPER,
            SCISSORS,
            ROCK, PAPER, SCISSORS, ROCK, // this is a pattern
            ROCK,
            ROCK,
            SCISSORS,
            PAPER,
            ROCK, PAPER, SCISSORS, // this is a pattern beginning
        };
        Item[] allComputersMoves = new Item[]{
            PAPER,
            SCISSORS,
            SCISSORS,
            PAPER, ROCK, SCISSORS, SCISSORS, // ...
            SCISSORS,
            ROCK,
            PAPER,
            ROCK,
            PAPER, ROCK, SCISSORS, // ...
        };

        // Avoiding randomness, each time return value should be deterministic for same parameters
        for (int i = 0; i < 20; i++) {
            assertEquals(
                PAPER, // PAPER beats ROCK which should be played next by player, RPS(R) pattern
                strategy
                    .guessTheItem(Arrays.asList(allPlayersMoves), Arrays.asList(allComputersMoves))
            );
        }
    }

    @Test
    public void pairHashCodeAndEqualsTest() {

        Set<Pair> pairs = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            pairs.add(new Pair(ROCK, ROCK));
            pairs.add(new Pair(PAPER, PAPER));
            pairs.add(new Pair(SCISSORS, SCISSORS));
        }
        assertEquals(3, pairs.size());

        assertEquals(new Pair(SCISSORS, PAPER), new Pair(SCISSORS, PAPER));
        assertEquals(new Pair(PAPER, ROCK), new Pair(PAPER, ROCK));

        assertNotEquals(new Pair(ROCK, ROCK), new Pair(PAPER, SCISSORS));
    }
}
