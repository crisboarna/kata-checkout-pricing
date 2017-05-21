package com.kata.pricing;

import com.kata.inventory.Item;
import com.kata.strategies.AbstractPricingStrategy;
import com.kata.strategies.PricingStrategy;
import com.kata.strategies.SimplePricingStrategy;

import java.util.*;


/**
 * Created by crist on 5/21/2017.
 */
public class PricingTerminal {
    private final Map<Class<? extends PricingStrategy>, PricingStrategy> strategyMap;

    public PricingTerminal() {
        this.strategyMap = new HashMap<>();
        this.addPricingStrategy(new SimplePricingStrategy());
    }

    public double getBasketTotal(Map<String,List<Item>> shoppingBasket){
        double total = 0;

        Map<String, List<Item>> basketCopy = new HashMap<>(shoppingBasket);

        List<AbstractPricingStrategy> sortedPricingStrategies = new ArrayList(strategyMap.values());
        Collections.sort(sortedPricingStrategies);

        for(PricingStrategy pricingStrategy : sortedPricingStrategies){
            total += pricingStrategy.calculate(basketCopy);
        }

        return total;
    }

    public void addPricingStrategy(PricingStrategy pricingStrategy){
        strategyMap.put(pricingStrategy.getClass(),pricingStrategy);
    }

    public void addPromotion(Class<? extends PricingStrategy> pricingStrategy, String promotionItem){
        strategyMap.get(pricingStrategy).addPromotionItem(promotionItem);
    }

    public void addPromotions(Class<? extends PricingStrategy> pricingStrategy, List<String> promotionItems){
        strategyMap.get(pricingStrategy).addPromotionItems(promotionItems);
    }
}
