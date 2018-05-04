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


//    @Test
//    public void parseRawDataIntoStringArrayTest(){
//        Integer expectedArraySize = 3;
//        ArrayList<String> items = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
//        Integer actualArraySize = items.size();
//        assertEquals(expectedArraySize, actualArraySize);
//    }
//
//    @Test
//    public void parseStringIntoItemTest() throws ItemParseException{
//        Item expected = new Item("milk", 3.23, "food","1/25/2016");
//        String myNormalized = itemParser.normalizeRawData(rawSingleItem);
//        Item actual = itemParser.parseStringIntoItem(myNormalized);
//        assertEquals(expected.toString(), actual.toString());
//    }
//
//    @Test(expected = ItemParseException.class)
//    public void parseBrokenStringIntoItemTest() throws ItemParseException{
//        itemParser.parseStringIntoItem(rawBrokenSingleItem);
//    }

//    @Test
//    public void testNormalizeRawData(){
//        String initial = "naMe:Co0kie@price:3.23%type:Food^expiration:1/11/2016##";
//        String expected = "name:cookie;price:3.23;type:food;expiration:1/11/2016##";
//        String actual = itemParser.normalizeRawData(initial);
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testToLowerCaseItem(){
//        String expected = rawSingleItem.toLowerCase();
//        String actual = itemParser.toLowerCaseString(rawSingleItem);
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testConvertWeirdCharactersToSemiColon(){
//        String initial = "^!@*%";
//        String expected = ";;;;;";
//        String actual = itemParser.convertWeirdCharactersToSemiColon(initial);
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testRemove0FromCookie(){
//        String initial = "c00kie";
//        String expected = "cookie";
//        String actual = itemParser.remove0FromCookie(initial);
//        Assert.assertEquals(expected, actual);
//    }

    @Test
    public void testToLowerCase(){

        System.out.println(itemParser.toLowerCase(rawSingleItem));
    }

}
