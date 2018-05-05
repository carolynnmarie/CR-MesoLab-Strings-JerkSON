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
    public ArrayList<Item> rawDataToItemArray(String rawData) throws ItemParseException {
        rawData = toLowerCase(rawData);
        rawData = fixCookie(rawData);
        ArrayList<String> rawString = parseRawDataIntoStringArray(rawData);
        return stringArrayToItemArray(rawString);

    }

    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        return splitStringWithRegexPattern(stringPattern, rawData);
    }

    public ArrayList<Item> stringArrayToItemArray(ArrayList<String> rawItems)throws ItemParseException{
        ArrayList<Item> itemArray = new ArrayList<>();
        for(String each: rawItems){
            Item item = parseStringIntoSingleItem(each);
            if(item.getName()!=null && item.getPrice()!=null && item.getType()!=null && item.getExpiration()!=null){
                itemArray.add(item);
            }
        }
        return itemArray;
    }

    public Item parseStringIntoSingleItem(String rawItem) throws ItemParseException{
        rawItem = toLowerCase(rawItem);
        ArrayList<String> keyValuePairs = findKeyValuePairsInRawItemData(rawItem);
        Item item = new Item(null,null,null,null);
        try{
        for(String each: keyValuePairs) {
            String[] array = each.split(":");
            if (array.length == 1) {
                throw new ItemParseException();
            } else if (array[0].matches("name")) {
                item.setName(array[1]);
            } else if (array[0].matches("price")) {
                item.setPrice(Double.parseDouble(array[1]));
            } else if (array[0].matches("type")) {
                item.setType(array[1]);
            } else if (array[0].matches("expiration")) {
                item.setExpiration(array[1]);
            }
        }
        } catch(ItemParseException e){
            exceptionCounter++;
        }

        return item;
    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[;^%*!@]";
        return splitStringWithRegexPattern(stringPattern , rawItem);
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<>(Arrays.asList(inputString.split(stringPattern)));
    }

    public String toLowerCase(String itemString){
        ArrayList<String> upperCase = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J",
                "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
        ArrayList<String> lowerCase = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","j",
                "k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));
        Pattern pattern = Pattern.compile(upperCase.get(0));
        Matcher matcher = pattern.matcher(itemString);
        String lower = matcher.replaceAll(lowerCase.get(0));
        for(int i = 1; i<upperCase.size(); i++){
            matcher.reset(lower);
            matcher.usePattern(Pattern.compile(upperCase.get(i)));
            lower = matcher.replaceAll(lowerCase.get(i));
        }
        return lower;
    }

    public String fixCookie(String raw){
        Pattern pattern = Pattern.compile("c..kie");
        Matcher matcher = pattern.matcher(raw);
        return matcher.replaceAll("cookie");

    }
}
