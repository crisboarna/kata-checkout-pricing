package com.kata.strategies;

import com.kata.inventory.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QuantityPromoPricingStrategy that = (QuantityPromoPricingStrategy) o;
        return numerator == that.numerator &&
                denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numerator, denominator);
    }
}
