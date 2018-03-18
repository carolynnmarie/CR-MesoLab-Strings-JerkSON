package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

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
    public void testCreateGroceryList1() {
        //Given
        ListCreation list = new ListCreation();
        ListEntryPair entry = new ListEntryPair("", 0.00);
        Item item1 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item2 = new Item("cookies", 2.50, "food", "12/03/2018");
        //When
        ArrayList<Item> itemObjects = new ArrayList<>();
        itemObjects.add(item1);
        itemObjects.add(item2);
        String expectedName = "milk";
        //Then
        ArrayList<ListEntryPair> groceries = list.createNameAndPriceList(itemObjects);
        entry = groceries.get(0);
        String actualName = entry.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testFormattedGroceryListString(){
        //Given
        ListCreation list = new ListCreation();
        ArrayList<Item> itemObjects = new ArrayList<>();
        Item item1 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item2 = new Item("cookies", 2.50, "food", "12/03/2018");
        Item item3 = new Item("apples", 0.35, "food","04/23/2018");
        Item item4 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item5 = new Item("milk", 2.00, "food", "1/25/2016");
        //When
        itemObjects.add(item1);
        itemObjects.add(item2);
        itemObjects.add(item3);
        itemObjects.add(item4);
        itemObjects.add(item5);
        //Then
        ArrayList<ListEntryPair> groceries = list.createNameAndPriceList(itemObjects);
        String actual = list.formattedGroceryListString(groceries);
        System.out.println(actual);
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
