package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.PATTERN;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.LITERAL;

public class ItemParser {

    public static int exceptionCounter;


    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String normalizedRawData = normalizeRawData(rawData);
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , normalizedRawData);
        return response;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException{
        ArrayList<String> itemPairs = new ArrayList<>();

        return null;
    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[;]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);
        return response;
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }


    public String normalizeRawData(String rawData){
        String normal = convertWeirdCharactersToSemiColon(rawData);
        String normal2 = toLowerCaseString(normal);
        return remove0FromCookie(normal2);

    }

    public String convertWeirdCharactersToSemiColon(String rawData) {
        ArrayList<String> wierdCharacters = new ArrayList<>(Arrays.asList("@", "^", "!", "*", "%"));
        Pattern pattern = Pattern.compile("@");
        Matcher matcher = pattern.matcher(rawData);
        String unWeird = matcher.replaceAll(";");
        for (String character : wierdCharacters) {
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
        for(int i = 0; i<upperCase.size(); i++){
            matcher.reset(lower);
            matcher.usePattern(Pattern.compile(upperCase.get(i)));
            lower = matcher.replaceAll(lowerCase.get(i));
        }
        return lower;
    }

    public String remove0FromCookie(String rawData){
        Pattern pattern = Pattern.compile("c..kie");
        Matcher matcher = pattern.matcher(rawData);
        String happySpelling = matcher.replaceAll("cookie");
        return happySpelling;
    }


}
