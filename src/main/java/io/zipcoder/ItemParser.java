package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    private static int exceptionCounter;

    public ItemParser(){
        exceptionCounter = 0;
    }

    public int getExceptionCounter(){
        return exceptionCounter;
    }

    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String normalizedRawData = normalizeRawData(rawData);
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , normalizedRawData);
        return response;
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
        String name = "";
        String priceString = "";
        Double price = 0.00;
        String type = "";
        String expiration = "";
        Pattern pattern = Pattern.compile("name:(\\w*);price:(\\d*.\\d*\\d*);type:(\\w*);expiration:(\\w*/\\w*/\\w*)");
        Matcher matcher = pattern.matcher(rawItem);
        if(matcher.find()) {
            try {
                name = matcher.group(1);
                priceString = matcher.group(2);
                price = Double.parseDouble(priceString);
                type = matcher.group(3);
                expiration = matcher.group(4);
                if (name.length() == 0 || priceString.length() == 0 || type.length() == 0 || expiration.length() == 0) {
                    exceptionCounter++;
                    throw new ItemParseException();
                }
            } catch (ItemParseException e) {
                exceptionCounter++;
            }
        }
        return new Item(name, price, type, expiration);
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<>(Arrays.asList(inputString.split(stringPattern)));
    }

    public String normalizeRawData(String rawData){
        String normal = convertWeirdCharactersToSemiColon(rawData);
        String normal2 = toLowerCaseString(normal);
        return remove0FromCookie(normal2);
    }

    public String convertWeirdCharactersToSemiColon(String rawData) {
        ArrayList<String> weirdCharacters = new ArrayList<>(Arrays.asList("^", "!", "*", "%"));
        Pattern pattern = Pattern.compile("@");
        Matcher matcher = pattern.matcher(rawData);
        String unWeird = matcher.replaceAll(";");
        for (String character : weirdCharacters) {
            matcher.reset(unWeird);
            matcher.usePattern(Pattern.compile(character, Pattern.LITERAL));
            unWeird = matcher.replaceAll(";");
        }
        return unWeird;
    }

    public String toLowerCaseString(String rawData){
        ArrayList<String> upperCase = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J",
                "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
        ArrayList<String> lowerCase = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","j",
                "k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));
        Pattern pattern = Pattern.compile("A");
        Matcher matcher = pattern.matcher(rawData);
        String lower = matcher.replaceAll("a");
        for(int i = 1; i<upperCase.size(); i++){
            matcher.reset(lower);
            matcher.usePattern(Pattern.compile(upperCase.get(i)));
            lower = matcher.replaceAll(lowerCase.get(i));
        }
        return lower;
    }

    public String remove0FromCookie(String rawData){
        Pattern pattern = Pattern.compile("c..kie");
        Matcher matcher = pattern.matcher(rawData);
        return matcher.replaceAll("cookie");

    }


}
