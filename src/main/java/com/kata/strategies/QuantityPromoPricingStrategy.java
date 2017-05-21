package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by crist on 5/17/2017.
*/
public class QuantityPromoPricingStrategy extends AbstractPricingStrategy{

    private final int numerator;
    private final int denominator;

    public QuantityPromoPricingStrategy(int numerator, int denominator){
        super(1);
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public QuantityPromoPricingStrategy(int numerator, int denominator, int priority) {
        super(priority);
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    protected Map<String, List<Item>> getStrategyEligibleItems(Map<String, List<Item>> basketItems) {
        return basketItems.entrySet().stream().filter(map-> promotionItems.contains(map.getKey()) && (map.getValue().size() >= numerator) )
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    @Override
    protected double getTotal(Map<String, List<Item>> eligibleItems) {
        double total = 0;

        for (String promotedItem : promotionItems) {
            List<Item> promotableItems = eligibleItems.get(promotedItem);

            if (promotableItems != null && promotableItems.size() > denominator) {
                double promotedTotal = (promotableItems.get(0).getPrice() * denominator) * promotableItems.size() / numerator;
                if (promotableItems.size() % numerator == 0) {
                    total += promotedTotal;
                } else {
                    double remainderTotal = promotableItems.get(0).getPrice() * promotableItems.size() % numerator;
                    total += promotedTotal + remainderTotal;
                }
            }
        }

        return total;
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
