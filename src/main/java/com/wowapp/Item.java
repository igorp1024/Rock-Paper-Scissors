package com.wowapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents play item: rock, paper or scissors.
 *
 * @author ip
 */
enum Item {
    ROCK("R"), PAPER("P"), SCISSORS("S");

    private String value;
    private static Map<String, Item> valuesCache = new HashMap<>(Item.values().length);

    static {
        for (Item nextItem : Item.values()) {
            valuesCache.put(nextItem.value, nextItem);
        }
    }

    Item(String value) {
        this.value = value.toUpperCase();
    }

    public boolean beats(Item item) {
        int indexDifference = this.ordinal() - item.ordinal();
        return (indexDifference == 1) || (indexDifference == -1 * (Item.values().length - 1));
    }

    public boolean drawFor(Item item) {
        return this.ordinal() == item.ordinal();
    }

    public static Item findValue(String str) {
        return valuesCache.get(str.toUpperCase());
    }
}
