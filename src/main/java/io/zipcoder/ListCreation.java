package io.zipcoder;

import java.util.ArrayList;

public class ListCreation {
//I used an ArrayList of Entry pairs instead of a map because, first, the output.txt indicates we are
//count the number of occurrences of an item and maps don't support multiple keys with the same name, and
//in JSON there is no such restriction on key/value pairs.  I found it most efficient to put each item name/ item price
//pair in an ArrayList and use regex to count the number of duplicates of each different key

    private ListEntryPair entry;
    private ArrayList<ListEntryPair> groceryList;

    public ListCreation(){
        this.entry = new ListEntryPair("", 0.00);
        this.groceryList = new ArrayList<>();
    }

    public ArrayList<ListEntryPair> createGroceryList(ArrayList<Item> itemObjects) {
        for(Item item: itemObjects){
            String name = item.getName();
            Double price = item.getPrice();
            entry = new ListEntryPair(name,price);
            groceryList.add(entry);
        }
        return groceryList;
    }

}
