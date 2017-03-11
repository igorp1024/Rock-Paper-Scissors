package com.wowapp;

import com.wowapp.strategy.IStrategy;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.wowapp.Engine.MAXIMUM_ALLOWED_LOSES;
import static com.wowapp.Engine.ROUNDS_PER_STRATEGY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author ip
 */
public class EngineTest {

    private class MockEngine extends Engine {

        public MockEngine(IStrategy[] strategies) {
            super(strategies);
        }

        @Override
        protected IStrategy pickAStrategyToTest(Outcome lastOutcome) {
            return super.pickAStrategyToTest(lastOutcome);
        }

        @Override
        protected void stickToTheChosenStrategy(Outcome lastOutcome) {
            super.stickToTheChosenStrategy(lastOutcome);
        }

        @Override
        protected void pickANewBestStrategy() {
            super.pickANewBestStrategy();
        }

        public IStrategy getCurrentStrategy() {
            return currentStrategy;
        }


        public int getRound() {
            return round;
        }

        public int getLastLosesInARow() {
            return lastLosesInARow;
        }
    }

    @SuppressWarnings("Convert2Lambda")
    private IStrategy alwaysScissorsStrategy = new IStrategy() {
        @Override
        public Item guessTheItem(List<Item> playedItems) {
            return Item.SCISSORS;
        }
    };

    @SuppressWarnings("Convert2Lambda")
    private IStrategy alwaysPaperStrategy = new IStrategy() {
        @Override
        public Item guessTheItem(List<Item> playedItems) {
            return Item.PAPER;
        }
    };

    @Test
    public void testEngine() {

        // Engine with two strategies: "Always Scissors" and "Always Paper"
        MockEngine engine = Mockito.spy(
            new MockEngine(new IStrategy[]{alwaysScissorsStrategy, alwaysPaperStrategy})
        );

        // First round is necessary for engine ramp-up, or better say to initialize the engine
        // for the next round
        Item computersChoice = engine.makeChoice(null, null);
        Outcome lastOutcome = Outcome.check(computersChoice, Item.ROCK);

        //===========================================================================
        // Let's enforce the engine to choose the first strategy ("Always Scissors")
        //===========================================================================

        // First strategy validation ("Always Scissors")
        //=============================================
        for (int i = 0; i < ROUNDS_PER_STRATEGY; i++) {
            // Always playing PAPER vs "Always Scissors"
            lastOutcome = playRound(engine, Item.PAPER, lastOutcome);
        }
        // First ROUNDS_PER_STRATEGY rounds were first strategy validation
        verify(engine, times(ROUNDS_PER_STRATEGY)).pickAStrategyToTest(any(Outcome.class));
        verify(engine, never()).pickANewBestStrategy();
        verify(engine, never()).stickToTheChosenStrategy(any(Outcome.class));

        // Second strategy validation ("Always Paper")
        //=============================================
        for (int i = 0; i < ROUNDS_PER_STRATEGY; i++) {
            // Always playing SCISSORS vs "Always Paper"
            lastOutcome = playRound(engine, Item.SCISSORS, lastOutcome);
        }
        // Second ROUNDS_PER_STRATEGY rounds were second strategy validation
        verify(engine, times(2 * ROUNDS_PER_STRATEGY)).pickAStrategyToTest(any(Outcome.class));
        verify(engine, never()).pickANewBestStrategy();
        verify(engine, never()).stickToTheChosenStrategy(any(Outcome.class));

        // This round the strategy should be chosen
        //==========================================
        lastOutcome = playRound(engine, Item.SCISSORS, lastOutcome);
        assertEquals(alwaysScissorsStrategy, engine.getCurrentStrategy());
        assertNotEquals(alwaysPaperStrategy, engine.getCurrentStrategy());
        assertEquals(Outcome.DRAW, lastOutcome);
        verify(engine, times(1)).pickANewBestStrategy();

        // Now let's beat the chosen strategy and make the engine to make a choice again
        //===============================================================================
        lastOutcome = Outcome.LOSS;
        for (int i = 0; i < MAXIMUM_ALLOWED_LOSES; i++) {
            // Playing ROCK vs "Always Scissors"
            lastOutcome = playRound(engine, Item.ROCK, lastOutcome);
            assertEquals(Outcome.LOSS, lastOutcome);
        }
        verify(engine, times(MAXIMUM_ALLOWED_LOSES)).stickToTheChosenStrategy(any(Outcome.class));
        assertEquals(0, engine.getRound());
        assertEquals(MAXIMUM_ALLOWED_LOSES, engine.getLastLosesInARow());

        //=============================================================================
        // Now let's enforce the engine to choose the second strategy ("Always Paper")
        //=============================================================================

        // First strategy validation ("Always Scissors")
        //=============================================
        for (int i = 0; i < ROUNDS_PER_STRATEGY; i++) {
            // Always playing ROCK vs "Always Scissors"
            lastOutcome = playRound(engine, Item.ROCK, lastOutcome);
        }
        // Second strategy validation ("Always Paper")
        //=============================================
        for (int i = 0; i < ROUNDS_PER_STRATEGY -1 ; i++) {
            // Always playing ROCK vs "Always Paper"
            lastOutcome = playRound(engine, Item.ROCK, lastOutcome);
        }
        // Fail "all the time winning strategy" a bit
        lastOutcome = playRound(engine, Item.SCISSORS, lastOutcome);

        // This round the strategy should be chosen, so test it
        //======================================================
        lastOutcome = playRound(engine, Item.PAPER, lastOutcome);
        assertEquals(alwaysPaperStrategy, engine.getCurrentStrategy());
        assertNotEquals(alwaysScissorsStrategy, engine.getCurrentStrategy());
        assertEquals(Outcome.DRAW, lastOutcome);
    }

    private Outcome playRound(MockEngine engine, Item playersChoice, Outcome lastOutcome) {
        Item computersChoice = engine.makeChoice(playersChoice, lastOutcome);
        return Outcome.check(computersChoice, playersChoice);
    }
}
