package com.wowapp.strategy;

import com.wowapp.Item;

import java.util.List;

/**
 * @author ip
 */
public class RotateStrategy implements IStrategy {

    @Override
    public Item guessTheItem(List<Item> playedItems) {
        // TODO: implement
        return Item.getRandom();
    }
}
