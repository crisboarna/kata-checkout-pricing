package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by crist on 5/18/2017.
 */
public class SimplePricingStrategy extends AbstractPricingStrategy {

    public SimplePricingStrategy(){
        super(0);
    }

    @Override
    protected Map<String, List<Item>> getStrategyEligibleItems(Map<String, List<Item>> basketItems) {
        return new HashMap<>(basketItems);
    }

    @Override
    protected double getTotal(Map<String, List<Item>> eligibleItems) {
        double total = 0;

        for(Map.Entry<String,List<Item>> entry : eligibleItems.entrySet()){
            total += entry.getValue().stream().map(Item::getPrice).reduce(0.0,(x,y)->x+y);
        }

        return total;
    }

    @Override
    public void addPromotionItem(String item){
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPromotionItems(List<String> items){
        throw new UnsupportedOperationException();
    }
}
