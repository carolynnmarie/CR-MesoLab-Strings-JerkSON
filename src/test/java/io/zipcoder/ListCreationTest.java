package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ListCreationTest {

    @Test
    public void testCreateGroceryList1() {
        //Given
        ListCreation list = new ListCreation();
        ListEntryPair entry = new ListEntryPair("", 0.00);
        Item item1 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item2 = new Item("cookies", 2.50, "food", "12/03/2018");
        ArrayList<Item> itemObjects = new ArrayList<>();
        //When
        itemObjects.add(item1);
        itemObjects.add(item2);
        String expectedName = "milk";
        ArrayList<ListEntryPair> groceries = list.createGroceryList(itemObjects);
        entry = groceries.get(0);
        String actualName = entry.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testCreateGroceryList2(){
        //Given
        ListCreation list = new ListCreation();
        ListEntryPair entry = new ListEntryPair("", 0.00);
        Item item1 = new Item("milk", 3.23, "food", "1/25/2016");
        Item item2 = new Item("cookies", 2.50, "food", "12/03/2018");
        Item item3 = new Item("apples", 0.35, "food","04/23/2018");
        ArrayList<Item> itemObjects = new ArrayList<>();
        //When
        itemObjects.add(item1);
        itemObjects.add(item2);
        itemObjects.add(item3);
        Double expectedPrice = 2.50;
        //Then
        ArrayList<ListEntryPair> groceries = list.createGroceryList(itemObjects);
        entry = groceries.get(1);
        Double actualPrice = entry.getPrice();
        Assert.assertEquals(expectedPrice, actualPrice);

    }

}
