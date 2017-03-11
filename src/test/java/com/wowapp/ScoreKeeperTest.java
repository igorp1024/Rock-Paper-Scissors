package com.wowapp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ip
 */
public class ScoreKeeperTest {

    @Test
    public void testWins() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();

        scoreKeeper.trackOutcome(Outcome.WIN);
        scoreKeeper.trackOutcome(Outcome.WIN);
        scoreKeeper.trackOutcome(Outcome.WIN);

        Assert.assertTrue(scoreKeeper.getWins() == 3);
        Assert.assertTrue(scoreKeeper.getDraws() == 0);
        Assert.assertTrue(scoreKeeper.getLosses() == 0);
    }

    @Test
    public void testDraws() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();

        scoreKeeper.trackOutcome(Outcome.DRAW);
        scoreKeeper.trackOutcome(Outcome.DRAW);
        scoreKeeper.trackOutcome(Outcome.DRAW);

        Assert.assertTrue(scoreKeeper.getWins() == 0);
        Assert.assertTrue(scoreKeeper.getDraws() == 3);
        Assert.assertTrue(scoreKeeper.getLosses() == 0);
    }

    @Test
    public void testLoses() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();

        scoreKeeper.trackOutcome(Outcome.LOSS);
        scoreKeeper.trackOutcome(Outcome.LOSS);
        scoreKeeper.trackOutcome(Outcome.LOSS);

        Assert.assertTrue(scoreKeeper.getWins() == 0);
        Assert.assertTrue(scoreKeeper.getDraws() == 0);
        Assert.assertTrue(scoreKeeper.getLosses() == 3);
    }

    @Test
    public void testMixed() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();

        scoreKeeper.trackOutcome(Outcome.WIN);
        scoreKeeper.trackOutcome(Outcome.WIN);
        Assert.assertTrue(scoreKeeper.getLastOutcome() == Outcome.WIN);

        scoreKeeper.trackOutcome(Outcome.DRAW);
        scoreKeeper.trackOutcome(Outcome.DRAW);
        scoreKeeper.trackOutcome(Outcome.DRAW);
        Assert.assertTrue(scoreKeeper.getLastOutcome() == Outcome.DRAW);

        scoreKeeper.trackOutcome(Outcome.LOSS);
        scoreKeeper.trackOutcome(Outcome.LOSS);
        scoreKeeper.trackOutcome(Outcome.LOSS);
        scoreKeeper.trackOutcome(Outcome.LOSS);
        Assert.assertTrue(scoreKeeper.getLastOutcome() == Outcome.LOSS);

        Assert.assertTrue(scoreKeeper.getWins() == 2);
        Assert.assertTrue(scoreKeeper.getDraws() == 3);
        Assert.assertTrue(scoreKeeper.getLosses() == 4);
    }
}
