package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by crist on 5/18/2017.
 */
public class WeightedPricingStrategy extends AbstractPricingStrategy {

    public WeightedPricingStrategy(){
        super(1);
    }

    public WeightedPricingStrategy(int priority) {
        super(priority);
    }

    @Override
    public double calculate(Map<String, List<Item>> basketItems) {
        double total = 0;
        List<String> processedItems = new ArrayList<>();

        for(String promotionalItem : promotionItems){
            List<Item> promotionableItems = basketItems.get(promotionalItem);

            if(promotionableItems != null) {
                total += promotionableItems.stream().map(item -> item.getPrice() * item.getQuanity()).reduce(0.0, (x, y) -> x + y);
                processedItems.add(promotionalItem);
            }
        }

        basketItems.keySet().removeAll(processedItems);

        return total;
    }
}
