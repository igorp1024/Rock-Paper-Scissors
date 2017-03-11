package com.wowapp;

import com.wowapp.strategy.IStrategy;
import com.wowapp.strategy.RotateStrategy;

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

        List<Item> playedItems = new ArrayList<>();

        positiveTest(playedItems);
        negativeTest(playedItems);
    }

    private void negativeTest(List<Item> playedItems) {
        IStrategy strategy = new RotateStrategy();

        playedItems.add(Item.ROCK);
        assertNotEquals(Item.ROCK, strategy.guessTheItem(playedItems));

        playedItems.add(Item.ROCK);
        assertNotEquals(Item.SCISSORS, strategy.guessTheItem(playedItems));

        playedItems.add(Item.SCISSORS);
        assertNotEquals(Item.SCISSORS, strategy.guessTheItem(playedItems));

        playedItems.add(Item.SCISSORS);
        assertNotEquals(Item.PAPER, strategy.guessTheItem(playedItems));

        playedItems.add(Item.PAPER);
        assertNotEquals(Item.PAPER, strategy.guessTheItem(playedItems));

        playedItems.add(Item.PAPER);
        assertNotEquals(Item.ROCK, strategy.guessTheItem(playedItems));
    }

    private void positiveTest(List<Item> playedItems) {
        IStrategy strategy = new RotateStrategy();
        playedItems.add(Item.PAPER);
        assertEquals(Item.SCISSORS, strategy.guessTheItem(playedItems));

        playedItems.add(Item.PAPER);
        assertEquals(Item.SCISSORS, strategy.guessTheItem(playedItems));

        playedItems.add(Item.PAPER);
        assertEquals(Item.SCISSORS, strategy.guessTheItem(playedItems));

        playedItems.add(Item.ROCK);
        assertEquals(Item.PAPER, strategy.guessTheItem(playedItems));

        playedItems.add(Item.PAPER);
        assertEquals(Item.SCISSORS, strategy.guessTheItem(playedItems));

        playedItems.add(Item.SCISSORS);
        assertEquals(Item.ROCK, strategy.guessTheItem(playedItems));

        playedItems.add(Item.SCISSORS);
        assertEquals(Item.ROCK, strategy.guessTheItem(playedItems));

        playedItems.add(Item.PAPER);
        assertEquals(Item.SCISSORS, strategy.guessTheItem(playedItems));

        playedItems.add(Item.ROCK);
        assertEquals(Item.PAPER, strategy.guessTheItem(playedItems));

        playedItems.add(Item.SCISSORS);
        assertEquals(Item.ROCK, strategy.guessTheItem(playedItems));
    }
}
