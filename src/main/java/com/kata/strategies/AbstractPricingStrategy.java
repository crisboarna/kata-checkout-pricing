package com.kata.strategies;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
