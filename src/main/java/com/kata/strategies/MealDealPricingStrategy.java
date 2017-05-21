package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by crist on 5/21/2017.
 */
public class MealDealPricingStrategy extends AbstractPricingStrategy {

    private final Set<Item> snacks;
    private final Set<Item> drinks;

    public MealDealPricingStrategy(){
        super(10);
        this.snacks = new HashSet<>();
        this.drinks = new HashSet<>();
    }

    public MealDealPricingStrategy(int priority) {
        super(priority);
        this.snacks = new HashSet<>();
        this.drinks = new HashSet<>();
    }

    @Override
    protected Map<String, List<Item>> getStrategyEligibleItems(Map<String, List<Item>> basketItems) {
        return null;
    }

    @Override
    protected double getTotal(Map<String, List<Item>> eligibleItems) {
        return 0;
    }

    //equivalence based on priority only, only present to highlight that although extra fields added, priority is only
    //value used
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }

    //maintain equals-boolean contract
    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
