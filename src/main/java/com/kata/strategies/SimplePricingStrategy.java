package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by crist on 5/18/2017.
 */
public class SimplePricingStrategy implements PricingStrategy {

    private static final int priority = 0;

    @Override
    public double calculate(Map<String, List<Item>> basketItems) {
        double total = 0;
        List<String> parsedItems = new ArrayList<>();

        for(Map.Entry<String,List<Item>> entry : basketItems.entrySet()){
            total += entry.getValue().stream().map(Item::getPrice).reduce(0.0,(x,y)->x+y);
            parsedItems.add(entry.getKey());
        }

        basketItems.keySet().removeAll(parsedItems);

        return total;
    }

    @Override
    public void addPromotionItem(String item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPromotionItems(List<String> items) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
