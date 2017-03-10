package com.wowapp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ip
 */
public class ItemTest {

    @Test
    public void testRockItems() {
        Assert.assertFalse(Item.ROCK.beats(Item.ROCK));
        Assert.assertFalse(Item.ROCK.beats(Item.PAPER));
        Assert.assertTrue(Item.ROCK.beats(Item.SCISSORS));

        Assert.assertTrue(Item.ROCK.drawFor(Item.ROCK));
        Assert.assertFalse(Item.ROCK.drawFor(Item.PAPER));
        Assert.assertFalse(Item.ROCK.drawFor(Item.SCISSORS));
    }

    @Test
    public void testPaperItems() {
        Assert.assertTrue(Item.PAPER.beats(Item.ROCK));
        Assert.assertFalse(Item.PAPER.beats(Item.PAPER));
        Assert.assertFalse(Item.PAPER.beats(Item.SCISSORS));

        Assert.assertFalse(Item.PAPER.drawFor(Item.ROCK));
        Assert.assertTrue(Item.PAPER.drawFor(Item.PAPER));
        Assert.assertFalse(Item.PAPER.drawFor(Item.SCISSORS));
    }

    @Test
    public void testScissorsItems() {
        Assert.assertFalse(Item.SCISSORS.beats(Item.ROCK));
        Assert.assertTrue(Item.SCISSORS.beats(Item.PAPER));
        Assert.assertFalse(Item.SCISSORS.beats(Item.SCISSORS));

        Assert.assertFalse(Item.SCISSORS.drawFor(Item.ROCK));
        Assert.assertFalse(Item.SCISSORS.drawFor(Item.PAPER));
        Assert.assertTrue(Item.SCISSORS.drawFor(Item.SCISSORS));
    }
}
