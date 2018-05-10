package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;


public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        ItemParser parser = new ItemParser();
        ArrayList<Item> items = parser.rawDataToItemArray(result);
        String list = parser.itemArrayToString(items);
        return list;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        ItemParser parse = new ItemParser();
        System.out.println(output);
        System.out.println(parse.getExceptionCounter());
        // TODO: parse the data in output into items, and display to console.
    }
}
