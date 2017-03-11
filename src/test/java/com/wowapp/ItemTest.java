package com.wowapp;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author ip
 */
public class ItemTest {

    @Test
    public void testRockItems() {
        assertFalse(Item.ROCK.beats(Item.ROCK));
        assertFalse(Item.ROCK.beats(Item.PAPER));
        assertTrue(Item.ROCK.beats(Item.SCISSORS));

        assertTrue(Item.ROCK.drawFor(Item.ROCK));
        assertFalse(Item.ROCK.drawFor(Item.PAPER));
        assertFalse(Item.ROCK.drawFor(Item.SCISSORS));
    }

    @Test
    public void testPaperItems() {
        assertTrue(Item.PAPER.beats(Item.ROCK));
        assertFalse(Item.PAPER.beats(Item.PAPER));
        assertFalse(Item.PAPER.beats(Item.SCISSORS));

        assertFalse(Item.PAPER.drawFor(Item.ROCK));
        assertTrue(Item.PAPER.drawFor(Item.PAPER));
        assertFalse(Item.PAPER.drawFor(Item.SCISSORS));
    }

    @Test
    public void testScissorsItems() {
        assertFalse(Item.SCISSORS.beats(Item.ROCK));
        assertTrue(Item.SCISSORS.beats(Item.PAPER));
        assertFalse(Item.SCISSORS.beats(Item.SCISSORS));

        assertFalse(Item.SCISSORS.drawFor(Item.ROCK));
        assertFalse(Item.SCISSORS.drawFor(Item.PAPER));
        assertTrue(Item.SCISSORS.drawFor(Item.SCISSORS));
    }

    @Test
    public void testRandomItems() {
        int maxItemsToGenerate = 1000;
        Set<Item> randomItems = new HashSet<>(maxItemsToGenerate);
        for (int i = 0; i < maxItemsToGenerate; i++) {
            Item randomItem = Item.getRandom();
            assertNotNull(randomItem);
            randomItems.add(randomItem);
        }
        // We should get all values on such a big amount of invocations of Item.getRandom()
        assertTrue(randomItems.size() == Item.values().length);
    }
}
