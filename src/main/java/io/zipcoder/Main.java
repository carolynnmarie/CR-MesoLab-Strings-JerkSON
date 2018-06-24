package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;


public class Main {

    public ArrayList<Item> readRawDataToItemArray() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        ItemParser parser = new ItemParser();
        ArrayList<Item> items = parser.rawDataToItemArray(result);
        return items;
    }

    public static void main(String[] args) throws Exception{
        ItemParser parse = new ItemParser();
        ListCreation create = new ListCreation();
        ArrayList<Item> output = (new Main()).readRawDataToItemArray();
        String out = create.createGroceryList(output);
        System.out.println(out);
    }
}
