package com.kata.strategies;

import com.kata.inventory.Item;
import com.kata.inventory.SimpleItem;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by crist on 5/18/2017.
 */
public class QuantityPromoPricingStrategyTest {

    private PricingStrategy strategy;
    private static final String YOGURT = "Yogurt";

    @Test
    public void testOneItemNoPromoApplied(){
        Item item = new SimpleItem(YOGURT,5.0);

        strategy = new QuantityPromoPricingStrategy(3,2);
        strategy.addPromotionItem(YOGURT);

        Map<String,List<Item>> map = new HashMap<>();
        map.put(YOGURT, Arrays.asList(item));

        assertEquals("1 item no promo applied, pass-through",0.0,strategy.calculate(map),0.0);
    }

    @Test
    public void testTwoItemsFromThreeForOnePromo(){
        Item item = new SimpleItem(YOGURT,5.0);
        Item item2 = new SimpleItem(YOGURT,5.0);

        strategy = new QuantityPromoPricingStrategy(3,2);
        strategy.addPromotionItem(YOGURT);

        Map<String,List<Item>> map = new HashMap<>();
        map.put(YOGURT, Arrays.asList(item,item2));

        assertEquals("1 item no promo applied, pass-through",0.0,strategy.calculate(map),0.0);
    }

    @Test
    public void testThreeForTwo(){
        Item item = new SimpleItem(YOGURT,5.0);
        Item item2 = new SimpleItem(YOGURT,5.0);
        Item item3 = new SimpleItem(YOGURT,5.0);

        strategy = new QuantityPromoPricingStrategy(3,2);
        strategy.addPromotionItem(YOGURT);

        Map<String,List<Item>> map = new HashMap<>();
        map.put(YOGURT, Arrays.asList(item,item2,item3));

        assertEquals("3 for 2 applied",10.0,strategy.calculate(map),0.0);
    }

    @Test
    public void testSixForFourPromo(){
        Item item = new SimpleItem(YOGURT,5.0);
        Item item2 = new SimpleItem(YOGURT,5.0);
        Item item3 = new SimpleItem(YOGURT,5.0);
        Item item4 = new SimpleItem(YOGURT,5.0);
        Item item5 = new SimpleItem(YOGURT,5.0);
        Item item6 = new SimpleItem(YOGURT,5.0);

        strategy = new QuantityPromoPricingStrategy(3,2);
        strategy.addPromotionItem(YOGURT);

        Map<String,List<Item>> map = new HashMap<>();
        map.put(YOGURT, Arrays.asList(item,item2,item3,item4,item5,item6));

        assertEquals("6 for 4 applied",20.0,strategy.calculate(map),0.0);
    }

    @Test
    public void testTwoForOnePromo(){
        Item item = new SimpleItem(YOGURT,5.0);
        Item item2 = new SimpleItem(YOGURT,5.0);

        strategy = new QuantityPromoPricingStrategy(2,1);
        strategy.addPromotionItem(YOGURT);

        Map<String,List<Item>> map = new HashMap<>();
        map.put(YOGURT, Arrays.asList(item,item2));

        assertEquals("2 for 1",5.0,strategy.calculate(map),0.0);
    }
}