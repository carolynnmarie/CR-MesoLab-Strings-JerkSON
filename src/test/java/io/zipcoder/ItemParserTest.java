package io.zipcoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemParserTest {

    private String rawSingleItem =    "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawSingleItemIrregularSeperatorSample = "naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##";

    private String rawBrokenSingleItem =    "naMe:Milk;price:3.23;type:;expiration:1/25/2016##";

    private String rawMultipleItems = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##"
                                      +"naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##"
                                      +"NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##";
    private ItemParser itemParser;

    @Before
    public void setUp(){
        itemParser = new ItemParser();
    }

    @Test
    public void rawDataToItemArrayTest() throws ItemParseException {
        String expected = "name: milk, price: 3.23, type: food, expiration: 1/25/2016";
        ArrayList<Item> items = itemParser.rawDataToItemArray(rawSingleItem);
        String actual = itemParser.itemArrayToString(items);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseRawDataIntoStringArrayTest(){
        Integer expectedArraySize = 3;
        ArrayList<String> items = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
        Integer actualArraySize = items.size();
        Assert.assertEquals(expectedArraySize, actualArraySize);
    }

    @Test
    public void parseStringIntoItemTest() throws ItemParseException{
        Item expected = new Item("milk", 3.23, "food","1/25/2016##");
        Item actual = itemParser.parseStringIntoSingleItem(rawSingleItem);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void parseBrokenStringIntoItemTest() throws ItemParseException{
        itemParser.parseStringIntoSingleItem(rawBrokenSingleItem);
        int e = 1;
        int a = itemParser.getExceptionCounter();
        Assert.assertEquals(e, a);
    }


    @Test
    public void testToLowerCaseString(){
        String expected = itemParser.toLowerCaseString(rawSingleItem);
        String actual = "name:milk;price:3.23;type:food;expiration:1/25/2016##";
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testFixCookie(){
        String initial = "c00kie";
        String expected = "cookie";
        String actual = itemParser.fixCookie(initial);
        Assert.assertEquals(expected, actual);
    }

}
