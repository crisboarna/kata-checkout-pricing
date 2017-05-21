package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.*;

/**
 * Created by crist on 5/18/2017.
 */
public abstract class AbstractPricingStrategy implements PricingStrategy, Comparable<AbstractPricingStrategy> {

    protected final Set<String> promotionItems;
    protected final int priority;

    protected AbstractPricingStrategy(int priority){
        this.priority = priority;
        this.promotionItems = new HashSet<>();
    }

    public double calculate(Map<String, List<Item>> basketItems){
        Map<String,List<Item>> eligibleItems = getStrategyEligibleItems(basketItems);

        removeProcessedItems(basketItems,eligibleItems);

        if(eligibleItems != null && !eligibleItems.isEmpty()){
            return getTotal(eligibleItems);
        }

        return 0;
    }

    private void removeProcessedItems(Map<String, List<Item>> basketItems, Map<String, List<Item>> eligibleItems) {
        basketItems.entrySet().removeAll(eligibleItems.entrySet());
    }

    protected abstract Map<String,List<Item>> getStrategyEligibleItems(Map<String,List<Item>> basketItems);

    protected abstract double getTotal(Map<String,List<Item>> eligibleItems);

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

    @Override
    public int compareTo(AbstractPricingStrategy o) {
        if (this.getPriority() < o.getPriority()) {
            return 1;
        } else if (this.getPriority() > o.getPriority()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPricingStrategy that = (AbstractPricingStrategy) o;
        return priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority);
    }
}
