package com.wowapp.strategy.pattern;

import com.wowapp.Item;
import com.wowapp.strategy.IStrategy;

import org.junit.Test;

import java.util.Arrays;

import static com.wowapp.Item.PAPER;
import static com.wowapp.Item.ROCK;
import static com.wowapp.Item.SCISSORS;
import static org.junit.Assert.assertEquals;

/**
 * @author ip
 */
public class PlainPatternStrategyTest {

    @Test
    public void test() {

        IStrategy strategy = new PlainPatternStrategy();

        Item[] allPlayersMoves = new Item[]{
            SCISSORS,
            PAPER,
            SCISSORS,
            ROCK,
            PAPER,
            ROCK,
            SCISSORS,
            SCISSORS,
            ROCK, SCISSORS, ROCK, PAPER, SCISSORS, // this is a pattern
            ROCK,
            SCISSORS,
            PAPER,
            PAPER,
            PAPER,
            ROCK,
            ROCK, SCISSORS, ROCK, PAPER, // this is a pattern beginning
        };

        // Avoiding randomness, each time return value should be deterministic for same parameters
        for (int i = 0; i < 20; i++) {
            assertEquals(
                ROCK, // ROCK beats SCISSORS (which is next in the RSRP(S) pattern the player uses)
                strategy.guessTheItem(Arrays.asList(allPlayersMoves), null)
            );
        }
    }
}
