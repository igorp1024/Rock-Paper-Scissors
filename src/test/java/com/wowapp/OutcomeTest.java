package com.wowapp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ip
 */
public class OutcomeTest {

    @Test
    public void testOutcomes() {
        Assert.assertEquals(Outcome.WIN, Outcome.check(Item.ROCK, Item.SCISSORS));
        Assert.assertNotEquals(Outcome.LOSS, Outcome.check(Item.ROCK, Item.SCISSORS));
        Assert.assertNotEquals(Outcome.DRAW, Outcome.check(Item.ROCK, Item.SCISSORS));

        Assert.assertEquals(Outcome.WIN, Outcome.check(Item.SCISSORS, Item.PAPER));
        Assert.assertNotEquals(Outcome.LOSS, Outcome.check(Item.SCISSORS, Item.PAPER));
        Assert.assertNotEquals(Outcome.DRAW, Outcome.check(Item.SCISSORS, Item.PAPER));

        Assert.assertEquals(Outcome.WIN, Outcome.check(Item.PAPER, Item.ROCK));
        Assert.assertNotEquals(Outcome.LOSS, Outcome.check(Item.PAPER, Item.ROCK));
        Assert.assertNotEquals(Outcome.DRAW, Outcome.check(Item.PAPER, Item.ROCK));

        Assert.assertEquals(Outcome.DRAW, Outcome.check(Item.ROCK, Item.ROCK));
        Assert.assertNotEquals(Outcome.WIN, Outcome.check(Item.ROCK, Item.ROCK));
        Assert.assertNotEquals(Outcome.LOSS, Outcome.check(Item.ROCK, Item.ROCK));

        Assert.assertEquals(Outcome.DRAW, Outcome.check(Item.SCISSORS, Item.SCISSORS));
        Assert.assertNotEquals(Outcome.WIN, Outcome.check(Item.SCISSORS, Item.SCISSORS));
        Assert.assertNotEquals(Outcome.LOSS, Outcome.check(Item.SCISSORS, Item.SCISSORS));

        Assert.assertEquals(Outcome.DRAW, Outcome.check(Item.PAPER, Item.PAPER));
        Assert.assertNotEquals(Outcome.WIN, Outcome.check(Item.PAPER, Item.PAPER));
        Assert.assertNotEquals(Outcome.LOSS, Outcome.check(Item.PAPER, Item.PAPER));

        Assert.assertEquals(Outcome.LOSS, Outcome.check(Item.ROCK, Item.PAPER));
        Assert.assertNotEquals(Outcome.WIN, Outcome.check(Item.ROCK, Item.PAPER));
        Assert.assertNotEquals(Outcome.DRAW, Outcome.check(Item.ROCK, Item.PAPER));

        Assert.assertEquals(Outcome.LOSS, Outcome.check(Item.SCISSORS, Item.ROCK));
        Assert.assertNotEquals(Outcome.WIN, Outcome.check(Item.SCISSORS, Item.ROCK));
        Assert.assertNotEquals(Outcome.DRAW, Outcome.check(Item.SCISSORS, Item.ROCK));

        Assert.assertEquals(Outcome.LOSS, Outcome.check(Item.PAPER, Item.SCISSORS));
        Assert.assertNotEquals(Outcome.WIN, Outcome.check(Item.PAPER, Item.SCISSORS));
        Assert.assertNotEquals(Outcome.DRAW, Outcome.check(Item.PAPER, Item.SCISSORS));
    }
}
