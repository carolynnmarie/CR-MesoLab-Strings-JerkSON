package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

public class ListEntryPairTest {

    @Test
    public void constructorTest(){
        Item item = new Item("milk", 3.23, "food","1/25/2016");
        String expectedName = "milk";
        Double expectedPrice = 3.23;
        String actualName = item.getName();
        Double actualPrice = item.getPrice();
        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedPrice, actualPrice);
    }
}
