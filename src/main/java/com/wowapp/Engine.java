package com.wowapp;

import com.wowapp.strategy.IStrategy;
import com.wowapp.strategy.RotateStrategy;
import com.wowapp.strategy.WeightedStrategy;
import com.wowapp.strategy.pattern.PairPatternStrategy;
import com.wowapp.strategy.pattern.PlainPatternStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main engine responsible for making choices which item to play. Meta-strategy is used by engine
 * instead of plain single strategy. It chooses different strategies when current seem to be
 * inefficient, rebuilding strategies' weight.
 *
 * @author ip
 */
public class Engine {

    protected static final int MAXIMUM_ALLOWED_LOSES = 4;
    protected static final int ROUNDS_PER_STRATEGY = 6;

    private IStrategy[] strategies;

    private Map<IStrategy, Integer> successStats;

    protected int round = 0, lastLosesInARow = 0;
    protected IStrategy currentStrategy;
    private List<Item> allPlayersMoves = new ArrayList<>();
    private List<Item> allComputersMoves = new ArrayList<>();

    public Engine() {
        this.strategies = new IStrategy[]{
            new RotateStrategy(),
            new WeightedStrategy(),
            new PlainPatternStrategy(),
            new PairPatternStrategy()
        };
        this.successStats = new HashMap<>(strategies.length);
    }

    public Engine(IStrategy[] strategies) {
        this.strategies = strategies;
        this.successStats = new HashMap<>(strategies.length);
    }

    public Item makeChoice(Item lastPlayersChoice, Outcome lastOutcome) {

        if (lastPlayersChoice != null && lastOutcome != null) {
            allPlayersMoves.add(lastPlayersChoice);
            Item item = pickStrategy(lastOutcome).guessTheItem(allPlayersMoves, allComputersMoves);
            allComputersMoves.add(item);
            round++;
            return item;
        } else {
            // The very beginning of the game, player's choice from previous round yet not available
            Item item = Item.ROCK;
            allComputersMoves.add(item);
            return item; // "Rock is for rookies" (common mantra in RPS circles)
        }
    }

    private IStrategy pickStrategy(Outcome lastOutcome) {

        // After ROUNDS_PER_STRATEGY times for each strategy stats are just gathered
        boolean statsAreBeingGathered = round < strategies.length * ROUNDS_PER_STRATEGY;
        boolean statsAreJustGathered = round == strategies.length * ROUNDS_PER_STRATEGY;

        if (statsAreBeingGathered) {

            return pickAStrategyToTest(lastOutcome);

        } else {

            // No need to gather success stats anymore
            if (statsAreJustGathered) {
                pickANewBestStrategy();
            } else {
                stickToTheChosenStrategy(lastOutcome);
            }

            // Return strategy which was either just picked up or being used as perspective one
            return currentStrategy;
        }
    }

    protected IStrategy pickAStrategyToTest(Outcome lastOutcome) {

        // When stats are still gathered, strategy is being used
        // for ROUNDS_PER_STRATEGY times in a row
        IStrategy strategy = strategies[round / ROUNDS_PER_STRATEGY];

        // On WIN or DRAW increase success count
        if (!Outcome.LOSS.equals(lastOutcome)) {
            if (successStats.containsKey(strategy)) {
                // Increase existing
                Integer previousWins = successStats.get(strategy);
                successStats.put(strategy, previousWins + 1);
            } else {
                // Or initialize with 1 value
                successStats.put(strategy, 1);
            }
        }

        return strategy;
    }

    protected void stickToTheChosenStrategy(Outcome lastOutcome) {
        // We are deep in gaming process with a perspective strategy. Gathering loses stats.
        if (Outcome.LOSS.equals(lastOutcome)) {
            if (++lastLosesInARow == MAXIMUM_ALLOWED_LOSES) {
                // Too much loses with current strategy, will pick a new one starting
                // the next round, but this round still using old one. To do this round value should
                // be zero. But since it is always incremented post factum, it should be -1 in order
                // to be zero next time
                round = -1;
                successStats.clear();
            }
        } else {
            lastLosesInARow = 0;
        }
    }

    protected void pickANewBestStrategy() {

        // Count total wins
        float totalWins = 0.0f;
        for (Integer nextWinsCount : successStats.values()) {
            totalWins += nextWinsCount;
        }

        // Find strategy with maximum weight
        float maxWeight = -1f;
        currentStrategy = null;
        for (Map.Entry<IStrategy, Integer> nextStats : successStats.entrySet()) {
            Integer nextWinsCount = nextStats.getValue();
            float currentWeight = nextWinsCount / totalWins;
            if (currentWeight > maxWeight) {
                maxWeight = currentWeight;
                currentStrategy = nextStats.getKey();
            }
        }
    }

    public void setStrategies(IStrategy[] strategies) {
        this.strategies = strategies;
        this.successStats = new HashMap<>(strategies.length);
    }
}
