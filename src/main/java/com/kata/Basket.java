package com.kata;

import com.kata.inventory.Item;
import com.kata.pricing.PricingTerminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by crist on 5/18/2017.
 */
public class Basket {

    private final Map<String,List<Item>> shoppingItems;
    private final PricingTerminal pricingTerminal;

    public Basket(PricingTerminal pricingTerminal) {
        this.pricingTerminal = pricingTerminal;
        this.shoppingItems = new HashMap<>();
    }

    public double getSum() {
        return pricingTerminal.getBasketTotal(shoppingItems);
    }

    public void addItem(Item item){
        if(!shoppingItems.containsKey(item.getName())){
            shoppingItems.put(item.getName(),new ArrayList<>());
        }
        shoppingItems.get(item.getName()).add(item);
    }

    public void addItems(List<Item> items) {
        for (Item item : items) {
            this.addItem(item);
        }
    }
}
