package com.kata.strategies;

import com.kata.inventory.Item;
import com.kata.inventory.SimpleItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by crist on 5/18/2017.
 */
public class WeightedPricingStrategyTest {

    private static final String YOGURT = "Yogurt";
    private static final String CHEESE = "Cheese";
    private PricingStrategy strategy;

    @Before
    public void init(){
        strategy = new WeightedPricingStrategy();
    }

    @Test
    public void testNoWeightedProduct(){
        Map<String,List<Item>> map = new HashMap<>();
        assertEquals("No promotion, no item",0.0,strategy.calculate(map),0);
    }

    @Test
    public void testOneWeightedProduct(){
        Item item = new SimpleItem(YOGURT,5.0,0.7);

        strategy.addPromotionItem(YOGURT);

        Map<String,List<Item>> map = new HashMap<>();
        map.put(YOGURT, Arrays.asList(item));

        assertEquals("0.7 kg Yogurt @ 5$/kg",3.5,strategy.calculate(map),0);
    }

    @Test
    public void testTwoWeightedProduct(){
        Item item = new SimpleItem(YOGURT,5.0, 0.7);
        Item item2 = new SimpleItem(CHEESE,2.0,1.0);

        strategy.addPromotionItems(Arrays.asList(YOGURT,CHEESE));

        Map<String,List<Item>> map = new HashMap<>();
        map.put(YOGURT, Arrays.asList(item,item2));

        assertEquals("0.7 kg Yogurt @ 5 $/kg + 2 kg Cheese @ 1 $/kg", 5.5,strategy.calculate(map),0);
    }
}