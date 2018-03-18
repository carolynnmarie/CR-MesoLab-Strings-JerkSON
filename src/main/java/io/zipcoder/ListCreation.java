package io.zipcoder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCreation {

    private ItemParser parser;

    public ListCreation() {
        this.parser = new ItemParser();
    }

    public String rawStringToFinishedGroceryList(String raw) throws Exception {
        ArrayList<String> rawItems = parser.parseRawDataIntoStringArray(raw);
        ArrayList<Item> groceryList = parser.stringArrayToItemArray(rawItems);
        return formattedGroceryListString(groceryList);
    }

    public String formattedGroceryListString(ArrayList<Item> groceryList) {
        TreeMap<Double,Integer> priceOccur = new TreeMap<>();
        String list = "";
        for (String key : keySet(groceryList)) {
            Pattern p = Pattern.compile(key);
            Matcher matcher = p.matcher(keyString(groceryList));
            int x = 0;
            while (matcher.find()) {
                x++;
            }
            if (x > 0) {
                if(x == 1){
                    list += String.format("name:%1$8s       seen: %2$d time \n=============       =============\n", key, x);
                }else{
                    list += String.format("name:%1$8s       seen: %2$d times\n=============       =============\n", key, x);
                }
                priceOccur = valueCount(groceryList, key);
                list += printValues(priceOccur);
            }
        }
        int errors = parser.getExceptionCounter();
        list += String.format("Errors              seen: %d times",errors);
        list = namesToCaps(list);
        return list;
    }

    public LinkedHashSet<String> keySet(ArrayList<Item> groceryList) {
        LinkedHashSet<String> keys = new LinkedHashSet<>();
        for (Item item : groceryList) {
            keys.add(item.getName());
        }
        return keys;
    }

    public String keyString(ArrayList<Item> groceryList) {
        String keys = "";
        for (Item item : groceryList) {
            keys += item.getName();
        }
        return keys;
    }

    public TreeMap<Double, Integer> valueCount(ArrayList<Item> groceryList,String key) {
        TreeMap<Double, Integer> priceOccurrences = new TreeMap<>();
        for (Item item : groceryList) {
            Double price = item.getPrice();
            if (key.equals(item.getName())) {
                if (!priceOccurrences.containsKey(item.getPrice())) {
                    priceOccurrences.put(price, 1);
                } else if (priceOccurrences.containsKey(item.getPrice())) {
                    priceOccurrences.replace(price, (priceOccurrences.get(price) + 1));
                }
            }
        }
        return priceOccurrences;
    }

    private String printValues(TreeMap<Double, Integer> priceOccurrences) {
        String valuesAndOccurrences = "";

        for (Map.Entry<Double, Integer> entry : priceOccurrences.descendingMap().entrySet()) {
            if(entry.getValue().equals(1)) {
                valuesAndOccurrences += String.format("Price:   %1$.2f       seen: %2$d time \n", entry.getKey(), entry.getValue());
            } else {
                valuesAndOccurrences += String.format("Price:   %1$.2f       seen: %2$d times\n", entry.getKey(), entry.getValue());
            }
            if(priceOccurrences.size()>1 && entry.getKey() != priceOccurrences.firstKey()) {
                valuesAndOccurrences += "-------------       -------------\n";
            } else if (priceOccurrences.size() == 1) {
                valuesAndOccurrences += "-------------       -------------\n";
            }
        }
        valuesAndOccurrences += "\n";
        return valuesAndOccurrences;
    }

    public String namesToCaps(String groceryList){
        String groceries = "";
        ArrayList<String> itemsLower = new ArrayList<>(Arrays.asList("bread","cookies","apples"));
        ArrayList<String> itemsUpper = new ArrayList<>(Arrays.asList("Bread","Cookies","Apples"));
        Pattern p = Pattern.compile("milk");
        Matcher m = p.matcher(groceryList);
        groceries = m.replaceAll("Milk");
        for(int i = 0; i<itemsLower.size(); i++){
            m.reset(groceries);
            m.usePattern(Pattern.compile(itemsLower.get(i)));
            groceries = m.replaceAll(itemsUpper.get(i));
        }
        return groceries;
    }

}


