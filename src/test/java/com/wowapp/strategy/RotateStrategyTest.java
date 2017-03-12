package com.wowapp.strategy;

import com.wowapp.Item;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author ip
 */
public class RotateStrategyTest {

    @Test
    public void test() {

        List<Item> allPlayersMoves = new ArrayList<>();

        positiveTest(allPlayersMoves);
        negativeTest(allPlayersMoves);
    }

    private void negativeTest(List<Item> playedItems) {
        IStrategy strategy = new RotateStrategy();

        playedItems.add(Item.ROCK);
        assertNotEquals(Item.ROCK, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.ROCK);
        assertNotEquals(Item.SCISSORS, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.SCISSORS);
        assertNotEquals(Item.SCISSORS, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.SCISSORS);
        assertNotEquals(Item.PAPER, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.PAPER);
        assertNotEquals(Item.PAPER, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.PAPER);
        assertNotEquals(Item.ROCK, strategy.guessTheItem(playedItems, null));
    }

    private void positiveTest(List<Item> playedItems) {
        IStrategy strategy = new RotateStrategy();
        playedItems.add(Item.PAPER);
        assertEquals(Item.SCISSORS, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.PAPER);
        assertEquals(Item.SCISSORS, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.PAPER);
        assertEquals(Item.SCISSORS, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.ROCK);
        assertEquals(Item.PAPER, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.PAPER);
        assertEquals(Item.SCISSORS, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.SCISSORS);
        assertEquals(Item.ROCK, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.SCISSORS);
        assertEquals(Item.ROCK, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.PAPER);
        assertEquals(Item.SCISSORS, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.ROCK);
        assertEquals(Item.PAPER, strategy.guessTheItem(playedItems, null));

        playedItems.add(Item.SCISSORS);
        assertEquals(Item.ROCK, strategy.guessTheItem(playedItems, null));
    }
}
