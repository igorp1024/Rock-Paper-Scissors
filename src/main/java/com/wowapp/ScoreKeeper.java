package com.wowapp;

/**
 * Score keeping data object.
 *
 * @author ip
 */
public class ScoreKeeper {

    private int wins = 0;
    private int losses = 0;
    private int draws = 0;

    private Outcome lastOutcome;

    /**
     * Tracks next outcome and updates inner statistic counters.
     *
     * @param lastOutcome another {@link Outcome} to track.
     */
    public void trackOutcome(Outcome lastOutcome) {

        this.lastOutcome = lastOutcome;

        switch (lastOutcome) {
            case WIN:
                wins += 1;
                break;
            case DRAW:
                draws += 1;
                break;
            case LOSS:
                losses += 1;
                break;
            default:
                throw new IllegalStateException("Abnormal round outcome");
        }
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public Outcome getLastOutcome() {
        return lastOutcome;
    }
}
