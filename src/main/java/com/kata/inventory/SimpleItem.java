package com.kata.inventory;

/**
 * Created by crist on 5/18/2017.
 */
public class SimpleItem implements Item {
    private final String name;
    private final double price;
    private double quantity;

    public SimpleItem(String name, double price) {
        this.price = price;
        this.name = name;
    }

    public SimpleItem(String name, double price, double quantity){
        this(name,price);
        this.quantity = quantity;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getQuanity() {
        return quantity;
    }

    @Override
    public String getName() {
        return name;
    }
}
