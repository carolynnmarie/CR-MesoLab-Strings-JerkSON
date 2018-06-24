package io.zipcoder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCreation {

    String rawString;
    String finishedList;
    ItemParser itemParser;

    public ListCreation(String rawString) {
        this.rawString = rawString;
        this.finishedList = "";
        this.itemParser = new ItemParser();
    }

    public Integer countDuplicatesName(ArrayList<Item> list, Item item) {
        Integer count = 0;
        for (Item id : list) {
            if (id.getName().equalsIgnoreCase(item.getName())) {
                count++;
            }
        }
        return count;
    }

    public Integer countDuplicatesPrice(ArrayList<Item> list, Item item) {
        Integer count = 0;
        for (Item id : list) {
            if (id.getPrice().equals(item.getPrice())) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Item> itemList() throws ItemParseException {
        return itemParser.rawDataToItemArray(rawString);
    }

    public String createGroceryList(ArrayList<Item> itemList){
        String list = "";

        for(Item item: itemList){

            list = String.format()
        }
    }
}





