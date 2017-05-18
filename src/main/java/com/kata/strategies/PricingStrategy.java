package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by crist on 5/18/2017.
 */
public interface PricingStrategy {
    double calculate(Map<String, List<Item>> basketItems);
    void addPromotionItem(String item);
    void addPromotionItems(List<String> items);
    int getPriority();
}
