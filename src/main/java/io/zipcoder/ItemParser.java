package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    private static int exceptionCounter;
    private Item item;

    public ItemParser(){
        exceptionCounter = 0;
        this.item = new Item(null, null, null, null);
    }

    public static void setExceptionCounter(int exceptionCounter) {
        ItemParser.exceptionCounter = exceptionCounter;
    }

    public int getExceptionCounter(){
        return exceptionCounter;
    }

    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        return splitStringWithRegexPattern(stringPattern, rawData);
    }

    public ArrayList<Item> stringArrayToItemArray(ArrayList<String> rawItems)throws ItemParseException{
        ArrayList<Item> itemArray = new ArrayList<>();
        for(String item: rawItems){
            Item it = parseStringIntoItem(item);
            if(!it.getName().equals("")) {
                itemArray.add(it);
            }
        }
        return itemArray;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException{
        Pattern p = Pattern.compile(":");
        return null;
    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[;|\\^|%|\\*|!|@]";
        return splitStringWithRegexPattern(stringPattern , rawItem);

    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<>(Arrays.asList(inputString.split(stringPattern)));
    }


}
