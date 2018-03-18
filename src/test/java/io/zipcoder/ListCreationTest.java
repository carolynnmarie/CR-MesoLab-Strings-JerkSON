package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ListCreationTest {

    @Test
    public void testRawToFinish() throws Exception {
        ListCreation list = new ListCreation();
        String raw = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016";
        try {
            String actual = list.rawStringToFinishedGroceryList(raw);
            System.out.println(actual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFormattedGroceryListString(){
        //Given
        ListCreation list = new ListCreation();
        Item item1 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item2 = new Item("cookies", 2.50, "food", "12/03/2018");
        Item item3 = new Item("apples", 0.35, "food","04/23/2018");
        Item item4 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item5 = new Item("milk", 2.00, "food", "1/25/2016");
        //When
        ArrayList<Item> itemObjects = new ArrayList<>(Arrays.asList(item1,item2,item3,item4,item5));
        //Then
        String groceryList = list.formattedGroceryListString(itemObjects);
        boolean expected = true;
        boolean actual = groceryList.contains("Milk");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testKeySet(){
        ListCreation list = new ListCreation();
        Item item1 = new Item("apples", 0.35, "food","04/23/2018");
        Item item2 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item3 = new Item("milk", 2.00, "food", "1/25/2016");
        ArrayList<Item> itemObjects = new ArrayList<>(Arrays.asList(item1,item2,item3));
        LinkedHashSet<String> actual = list.keySet(itemObjects);
        LinkedHashSet<String> expected = new LinkedHashSet<>(Arrays.asList("apples","milk"));
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testKeyString(){
        ListCreation list = new ListCreation();
        Item item1 = new Item("apples", 0.35, "food","04/23/2018");
        Item item2 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item3 = new Item("milk", 2.00, "food", "1/25/2016");
        ArrayList<Item> itemObjects = new ArrayList<>(Arrays.asList(item1, item2,item3));
        String expected = "applesmilkmilk";
        String actual = list.keyString(itemObjects);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testValueCount(){
        ListCreation list = new ListCreation();
        Item item1 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item2 = new Item("cookies", 2.50, "food", "12/03/2018");
        Item item3 = new Item("apples", 0.35, "food","04/23/2018");
        Item item4 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item5 = new Item("milk", 2.00, "food", "1/25/2016");
        ArrayList<Item> itemObjects = new ArrayList<>(Arrays.asList(item1, item2, item3, item4, item5));
        LinkedHashMap<Double,Integer> expected = new LinkedHashMap<>();
        expected.put(3.23,2);
        expected.put(2.00,1);
        LinkedHashMap<Double,Integer> actual = list.valueCount(itemObjects, "milk");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNamesToCaps(){
        ListCreation list = new ListCreation();
        String initial = "cookies";
        String expected = "Cookies";
        String actual = list.namesToCaps(initial);
        Assert.assertEquals(expected, actual);
    }

}
