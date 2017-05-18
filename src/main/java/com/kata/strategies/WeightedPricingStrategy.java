package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.*;

/**
 * Created by crist on 5/18/2017.
 */
public class WeightedPricingStrategy implements PricingStrategy {

    private final Set<String> promotionalItems;
    private final int priority;

    public WeightedPricingStrategy(){
        this.priority = 1;
        this.promotionalItems = new HashSet<>();
    }

    public WeightedPricingStrategy(int priority) {
        this.priority = priority;
        promotionalItems = new HashSet<>();
    }

    @Override
    public double calculate(Map<String, List<Item>> basketItems) {
        double total = 0;
        List<String> processedItems = new ArrayList<>();

        for(String promotionalItem : promotionalItems){
            List<Item> promotionableItems = basketItems.get(promotionalItem);

            if(promotionableItems != null) {
                total += promotionableItems.stream().map(item -> item.getPrice() * item.getQuanity()).reduce(0.0, (x, y) -> x + y);
                processedItems.add(promotionalItem);
            }
        }

        basketItems.keySet().removeAll(processedItems);

        return total;
    }

    @Override
    public void addPromotionItem(String item) {
        promotionalItems.add(item);
    }

    @Override
    public void addPromotionItems(List<String> items) {
        promotionalItems.addAll(items);
    }

    @Override
    public int getPriority() {
        return this.priority;
    }
}
