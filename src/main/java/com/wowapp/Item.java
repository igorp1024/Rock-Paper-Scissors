package com.wowapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Represents play item: rock, paper or scissors.
 *
 * @author ip
 */
public enum Item {

    // Next item should beat the previous
    ROCK("R"), PAPER("P"), SCISSORS("S");

    private String value;
    private static int valuesTotal = Item.values().length;
    private static Map<String, Item> valuesCache = new HashMap<>(valuesTotal);
    private static Random random = new Random(System.currentTimeMillis());

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
        return (indexDifference == 1) || (indexDifference == -1 * (valuesTotal - 1));
    }

    public boolean drawFor(Item item) {
        return this.ordinal() == item.ordinal();
    }

    public String getValue() {
        return value;
    }

    public static Item findValue(String str) {
        return valuesCache.get(str.toUpperCase());
    }

    public static Item getRandom() {
        return Item.values()[random.nextInt(valuesTotal)];
    }
}
