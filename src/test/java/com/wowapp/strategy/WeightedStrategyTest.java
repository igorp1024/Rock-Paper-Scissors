package com.wowapp.strategy;

import com.wowapp.Item;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.wowapp.Item.PAPER;
import static com.wowapp.Item.ROCK;
import static com.wowapp.Item.SCISSORS;
import static org.junit.Assert.assertEquals;

/**
 * @author ip
 */
public class WeightedStrategyTest {

    @Test
    public void test() {
        WeightedStrategy strategy = new WeightedStrategy();

        for (int i = 0; i < 1000; i++) {

            // Player plays mostly ROCK, let's play PAPER in return
            assertEquals(PAPER,
                         strategy.guessTheItem(
                             new ArrayList<>(
                                 Arrays.asList(
                                     new Item[]{ROCK, ROCK, PAPER, ROCK, ROCK, ROCK, SCISSORS}
                                 )
                             )
                         ));

            // Player plays mostly SCISSORS, let's play ROCK in return
            assertEquals(ROCK,
                         strategy.guessTheItem(
                             new ArrayList<>(
                                 Arrays.asList(
                                     new Item[]{ROCK, SCISSORS, PAPER, SCISSORS, SCISSORS}
                                 )
                             )
                         ));

            // Player plays mostly PAPER, let's play SCISSORS in return
            assertEquals(SCISSORS,
                         strategy.guessTheItem(
                             new ArrayList<>(
                                 Arrays.asList(
                                     new Item[]{ROCK, PAPER, SCISSORS, PAPER, PAPER, ROCK, PAPER}
                                 )
                             )
                         ));

        }
    }
}
