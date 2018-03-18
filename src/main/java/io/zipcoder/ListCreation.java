package io.zipcoder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCreation {

    private ListEntryPair entry;
    private ArrayList<ListEntryPair> groceryList;
    private ItemParser parser;

    public ListCreation() {
        this.entry = new ListEntryPair("", 0.00);
        this.groceryList = new ArrayList<>();
        this.parser = new ItemParser();
    }

    public String rawStringToFinishedGroceryList(String raw) throws Exception {
        ArrayList<String> rawItems = parser.parseRawDataIntoStringArray(raw);
        ArrayList<Item> itemArray = parser.stringArrayToItemArray(rawItems);
        ArrayList<ListEntryPair> groceryListArray = createNameAndPriceList(itemArray);
        String finish = formattedGroceryListString(groceryListArray);
        return finish;
    }

    public ArrayList<ListEntryPair> createNameAndPriceList(ArrayList<Item> itemObjects) {
        for (Item item : itemObjects) {
            String name = item.getName();
            Double price = item.getPrice();
            entry = new ListEntryPair(name, price);
            groceryList.add(entry);
        }
        return groceryList;
    }

    public String formattedGroceryListString(ArrayList<ListEntryPair> groceryList) {
        LinkedHashMap<Double,Integer> priceOccur = new LinkedHashMap<>();
        String list = "";
        for (String key : keySet(groceryList)) {
            Pattern p = Pattern.compile(key);
            Matcher matcher = p.matcher(keyAndValueString(groceryList));
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
        if(errors == 1){
            list += String.format("Errors              seen: %d time",errors);
        }else {
            list += String.format("Errors              seen: %d times",errors);
        }
        list = namesToCaps(list);
        return list;
    }

    private LinkedHashSet<String> keySet(ArrayList<ListEntryPair> groceryList) {
        LinkedHashSet<String> keys = new LinkedHashSet<>();
        for (ListEntryPair pair : groceryList) {
            keys.add(pair.getName());
        }
        return keys;
    }

    private String keyAndValueString(ArrayList<ListEntryPair> groceryList) {
        String keysAndValues = "";
        for (ListEntryPair pair : groceryList) {
            keysAndValues += pair.pairToString();
        }
        return keysAndValues;
    }

    private LinkedHashMap<Double, Integer> valueCount(ArrayList<ListEntryPair> groceryList,String key) {
        LinkedHashMap<Double, Integer> priceOccurrences = new LinkedHashMap<>();
        for (ListEntryPair pair : groceryList) {
            Double price = pair.getPrice();
            if (key.equals(pair.getName())) {
                if (!priceOccurrences.containsKey(pair.getPrice())) {
                    priceOccurrences.put(price, 1);
                } else if (priceOccurrences.containsKey(pair.getPrice())) {
                    priceOccurrences.replace(price, (priceOccurrences.get(price) + 1));
                }
            }
        }
        return priceOccurrences;
    }

    private String printValues(LinkedHashMap<Double, Integer> priceOccurrences) {
        String valuesAndOccurrences = "";
        for (Map.Entry<Double, Integer> entry : priceOccurrences.entrySet()) {
            if(entry.getValue().equals(1)) {
                valuesAndOccurrences += String.format("Price:   %1$.2f       seen: %2$d time \n", entry.getKey(), entry.getValue());
            } else {
                valuesAndOccurrences += String.format("Price:   %1$.2f       seen: %2$d times\n", entry.getKey(), entry.getValue());
            }
            valuesAndOccurrences += "-------------       -------------\n";

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


