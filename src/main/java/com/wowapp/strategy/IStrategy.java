package com.wowapp.strategy;

import com.wowapp.Item;

import java.util.List;

/**
 * Common strategy interface, which every strategy implements.
 *
 * @author ip
 */
public interface IStrategy {

    /**
     * Guesses the {@link Item} to beat the player's choice.
     *
     * @param allPlayersMoves   list of all previous player's moves (<tt>List</tt> of {@link
     *                          Item}).
     * @param allComputersMoves list of all previous computer's moves (<tt>List</tt> of {@link
     *                          Item}).
     */
    Item guessTheItem(List<Item> allPlayersMoves, List<Item> allComputersMoves);
}
