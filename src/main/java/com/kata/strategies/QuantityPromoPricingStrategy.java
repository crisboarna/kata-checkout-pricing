package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.*;

/**
 * Created by crist on 5/18/2017.
 */
public class QuantityPromoPricingStrategy implements PricingStrategy {

    private final Set<String> promotionItems;
    private final int numerator;
    private final int denominator;
    private final int priority;

    public QuantityPromoPricingStrategy(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
        this.priority = 1;
        this.promotionItems = new HashSet<>();
    }

    public QuantityPromoPricingStrategy(int numerator, int denominator, int priority) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.priority = priority;
        this.promotionItems = new HashSet<>();
    }

    @Override
    public double calculate(Map<String, List<Item>> basketItems) {
        double total = 0;
        List<String> parsedItems = new ArrayList<>();

        for(String promotedItem : promotionItems){
            List<Item> promotableItems = basketItems.get(promotedItem);

            if(promotableItems != null && promotableItems.size() > denominator) {
                double promotedTotal = (promotableItems.get(0).getPrice() * denominator) * promotableItems.size() / numerator;
                if (promotableItems.size() % numerator == 0) {
                    total += promotedTotal;
                    parsedItems.add(promotedItem);
                } else {
                    double remainderTotal = promotableItems.get(0).getPrice() * promotableItems.size() % numerator;
                    total += promotedTotal + remainderTotal;
                    parsedItems.add(promotedItem);
                }
                parsedItems.add(promotedItem);
            }
        }

        basketItems.keySet().removeAll(parsedItems);

        return total;
    }

    @Override
    public void addPromotionItem(String item){
        this.promotionItems.add(item);
    }

    @Override
    public void addPromotionItems(List<String> items){
        this.promotionItems.addAll(items);
    }

    @Override
    public int getPriority() {
        return this.priority;
    }
}
