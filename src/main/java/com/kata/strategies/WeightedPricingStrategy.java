package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    protected Map<String, List<Item>> getStrategyEligibleItems(Map<String, List<Item>> basketItems) {
        return basketItems.entrySet().stream().filter(map-> promotionItems.contains(map.getKey())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    @Override
    protected double getTotal(Map<String, List<Item>> eligibleItems) {
        double total = 0;

        for(String promotionalItem : promotionItems){
            List<Item> promotionableItems = eligibleItems.get(promotionalItem);

            if(promotionableItems != null) {
                total += promotionableItems.stream().map(item -> item.getPrice() * item.getQuanity()).reduce(0.0, (x, y) -> x + y);
            }
        }
        
        return total;
    }
}
