package com.kata.strategies;

import com.kata.Basket;
import com.kata.inventory.Item;
import com.kata.inventory.SimpleItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by crist on 5/18/2017.
 */
public class WeightedPricingStrategyTest {

    private static final String YOGURT = "Yogurt";
    private static final String CHEESE = "Cheese";
    private Basket basket;
    private PricingStrategy strategy;

    @Before
    public void init(){
        strategy = new WeightedPricingStrategy();
        basket = new Basket();
        basket.addPricingStrategy(strategy);
    }

    @Test
    public void testNoWeightedProduct(){
        assertEquals("No promotion, no item",0.0,basket.getTotal(),0);
    }

    @Test
    public void testOneWeightedProduct(){
        Item item = new SimpleItem(YOGURT,5.0,0.7);

        basket.addItem(item);
        basket.addPromotion(strategy.getClass(),YOGURT);

        assertEquals("0.7 kg Yogurt @ 5$/kg",3.5,basket.getTotal(),0);
    }

    @Test
    public void testTwoWeightedProduct(){
        Item item = new SimpleItem(YOGURT,5.0, 0.7);
        Item item2 = new SimpleItem(CHEESE,2.0,1.0);

        basket.addItems(Arrays.asList(item,item2));
        basket.addPromotions(strategy.getClass(),Arrays.asList(YOGURT,CHEESE));

        assertEquals("0.7 kg Yogurt @ 5 $/kg + 2 kg Cheese @ 1 $/kg", 5.5,basket.getTotal(),0);
    }
}