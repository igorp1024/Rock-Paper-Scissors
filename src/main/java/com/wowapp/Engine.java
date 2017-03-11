package com.wowapp;

import com.wowapp.strategy.IStrategy;
import com.wowapp.strategy.RotateStrategy;
import com.wowapp.strategy.WeightedStrategy;

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

    private static final int MAXIMUM_ALLOWED_LOSES = 4;
    private static final int ROUNDS_PER_STRATEGY = 6;

    private IStrategy[] strategies = new IStrategy[]{new RotateStrategy(), new WeightedStrategy()};
    private Map<IStrategy, Integer> successStats = new HashMap<>(strategies.length);

    private int round = 0, lastLosesInARow = 0;
    private IStrategy currentStrategy;
    private List<Item> playedItems = new ArrayList<>();

    public Item makeChoice(Item lastPlayersChoice, Outcome lastOutcome) {

        if (lastPlayersChoice != null && lastOutcome != null) {
            playedItems.add(lastPlayersChoice);
            Item item = pickStrategy(lastOutcome).guessTheItem(playedItems);
            round++;
            return item;
        } else {
            // The very beginning of the game, player's choice from previous round yet not available
            return Item.ROCK; // "Rock is for rookies" (common mantra in RPS circles)
        }
    }

    private IStrategy pickStrategy(Outcome lastOutcome) {

        // After ROUNDS_PER_STRATEGY times for each strategy stats are just gathered
        boolean statsAreBeingGathered = round < strategies.length * ROUNDS_PER_STRATEGY;
        boolean statsAreJustGathered = round == strategies.length * ROUNDS_PER_STRATEGY;

        if (statsAreBeingGathered) {

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

        } else {

            // No need to gather success stats anymore
            if (statsAreJustGathered) {
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
                    if (nextWinsCount / totalWins > maxWeight) {
                        currentStrategy = nextStats.getKey();
                    }
                }

            } else {
                // We are deep in gaming process with a perspective strategy. Gathering loses stats.
                if (Outcome.LOSS.equals(lastOutcome)) {
                    if (lastLosesInARow++ == MAXIMUM_ALLOWED_LOSES) {
                        // Too much loses with current strategy, will pick a new one starting
                        // the next round, but this round still using old one
                        round = 0;
                        successStats.clear();
                    }
                } else {
                    lastLosesInARow = 0;
                }
            }

            // Return strategy which was either just picked up or being used as perspective one
            return currentStrategy;
        }
    }
}
