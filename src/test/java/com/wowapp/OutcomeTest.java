package com.wowapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author ip
 */
public class OutcomeTest {

    @Test
    public void testOutcomes() {
        assertEquals(Outcome.WIN, Outcome.check(Item.ROCK, Item.SCISSORS));
        assertNotEquals(Outcome.LOSS, Outcome.check(Item.ROCK, Item.SCISSORS));
        assertNotEquals(Outcome.DRAW, Outcome.check(Item.ROCK, Item.SCISSORS));

        assertEquals(Outcome.WIN, Outcome.check(Item.SCISSORS, Item.PAPER));
        assertNotEquals(Outcome.LOSS, Outcome.check(Item.SCISSORS, Item.PAPER));
        assertNotEquals(Outcome.DRAW, Outcome.check(Item.SCISSORS, Item.PAPER));

        assertEquals(Outcome.WIN, Outcome.check(Item.PAPER, Item.ROCK));
        assertNotEquals(Outcome.LOSS, Outcome.check(Item.PAPER, Item.ROCK));
        assertNotEquals(Outcome.DRAW, Outcome.check(Item.PAPER, Item.ROCK));

        assertEquals(Outcome.DRAW, Outcome.check(Item.ROCK, Item.ROCK));
        assertNotEquals(Outcome.WIN, Outcome.check(Item.ROCK, Item.ROCK));
        assertNotEquals(Outcome.LOSS, Outcome.check(Item.ROCK, Item.ROCK));

        assertEquals(Outcome.DRAW, Outcome.check(Item.SCISSORS, Item.SCISSORS));
        assertNotEquals(Outcome.WIN, Outcome.check(Item.SCISSORS, Item.SCISSORS));
        assertNotEquals(Outcome.LOSS, Outcome.check(Item.SCISSORS, Item.SCISSORS));

        assertEquals(Outcome.DRAW, Outcome.check(Item.PAPER, Item.PAPER));
        assertNotEquals(Outcome.WIN, Outcome.check(Item.PAPER, Item.PAPER));
        assertNotEquals(Outcome.LOSS, Outcome.check(Item.PAPER, Item.PAPER));

        assertEquals(Outcome.LOSS, Outcome.check(Item.ROCK, Item.PAPER));
        assertNotEquals(Outcome.WIN, Outcome.check(Item.ROCK, Item.PAPER));
        assertNotEquals(Outcome.DRAW, Outcome.check(Item.ROCK, Item.PAPER));

        assertEquals(Outcome.LOSS, Outcome.check(Item.SCISSORS, Item.ROCK));
        assertNotEquals(Outcome.WIN, Outcome.check(Item.SCISSORS, Item.ROCK));
        assertNotEquals(Outcome.DRAW, Outcome.check(Item.SCISSORS, Item.ROCK));

        assertEquals(Outcome.LOSS, Outcome.check(Item.PAPER, Item.SCISSORS));
        assertNotEquals(Outcome.WIN, Outcome.check(Item.PAPER, Item.SCISSORS));
        assertNotEquals(Outcome.DRAW, Outcome.check(Item.PAPER, Item.SCISSORS));
    }
}
