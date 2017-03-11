package com.wowapp.strategy;

import com.wowapp.Item;

import java.io.Serializable;
import java.util.List;

/**
 * Common strategy interface.
 *
 * @author ip
 */
public interface IStrategy {

    Item guessTheItem(List<Item> playedItems);
}
