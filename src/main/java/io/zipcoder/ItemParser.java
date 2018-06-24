package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    private static int exceptionCounter;
    String rawData;

    public ItemParser(){
        exceptionCounter = exceptionCounter;
        this.rawData = rawData;
    }

    public int getExceptionCounter(){
        return exceptionCounter;
    }

    public ArrayList<Item> rawDataToItemArray(String rawData) throws ItemParseException {
        rawData = toLowerCaseString(rawData);
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
        rawItem = toLowerCaseString(rawItem);
        ArrayList<String> keyValuePairs = findKeyValuePairsInRawItemData(rawItem);
        Item item = new Item(null,null,null,null);
        try{
        for(String each: keyValuePairs) {
            String[] array = each.split(":");
            if (array.length == 1) {
                throw new ItemParseException();
            } else if (array[0].matches("name")) {
                item.setName(firstLetterToUpper(array[1]));
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
        return splitStringWithRegexPattern(stringPattern ,rawItem);
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<>(Arrays.asList(inputString.split(stringPattern)));
    }

    public String toLowerCaseString(String itemString){
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

    public String firstLetterToUpper(String name){
        ArrayList<String> lower = new ArrayList<>(Arrays.asList("b","c","m"));
        ArrayList<String> upper = new ArrayList<>(Arrays.asList("B","C","M"));
        String uCName = "";
        Pattern p = Pattern.compile("a");
        Matcher m = p.matcher(name.substring(0,1));
        if(m.matches()) {
            m.reset(name);
            uCName = m.replaceFirst("A");
            m.reset(name.substring(0,1));
        }
        for(int i = 0; i<lower.size(); i++){
            m.usePattern(Pattern.compile(lower.get(i)));
            if(m.matches()) {
                m.reset((name));
                uCName = m.replaceFirst(upper.get(i));
                m.reset(name.substring(0,1));
            }
        }
        return uCName;
    }



    public String fixCookie(String raw){
        Pattern pattern = Pattern.compile("c..kie");
        Matcher matcher = pattern.matcher(raw);
        return matcher.replaceAll("cookie");
    }

    public String itemArrayToString(ArrayList<Item> array){
        StringBuilder itemString = new StringBuilder();
        for(Item item: array){
            itemString.append(item.toString());
        }
        return itemString.toString();
    }


}
