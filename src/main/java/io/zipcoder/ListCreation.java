package io.zipcoder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCreation {

    ItemParser itemParser;

    public ListCreation() {
        this.itemParser = new ItemParser();
    }

    public String createGroceryList(ArrayList<Item> items) throws ItemParseException {
        StringBuilder builder = new StringBuilder();
        LinkedHashMap<String, Integer> names = nameList(items);
        LinkedHashMap<Double, Integer> prices = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : names.entrySet()) {
            for (int i = 0; i < items.size(); i++) {
                if (entry.getKey().equals(items.get(i).getName())) {
                    prices = priceList(items, entry.getKey());
                }
            }
            builder.append(String.format("Name: %7s       seen: %1d times\n=============       =============\n",
                    entry.getKey(), entry.getValue()))
                    .append(printPrices(prices));

        }
        builder.append(String.format("\nErrors              seen: %1d times\n", itemParser.getExceptionCounter()));
        return builder.toString();
    }

    public LinkedHashMap<String, Integer> nameList(ArrayList<Item> items) {
        LinkedHashMap<String, Integer> itemNames = new LinkedHashMap<>();
        for (Item item : items) {
            itemNames.put(item.getName(), countDuplicatesName(items, item));
        }
        return itemNames;
    }

    private Integer countDuplicatesName(ArrayList<Item> list, Item item) {
        Integer count = 0;
        for (Item id : list) {
            if (id.getName().equalsIgnoreCase(item.getName())) {
                count++;
            }
        }
        return count;
    }

    public LinkedHashMap<Double, Integer> priceList(ArrayList<Item> items, String name) {
        LinkedHashMap<Double, Integer> prices = new LinkedHashMap<>();
        for (Item item : items) {
            if (item.getName().equals(name)) {
                prices.put(item.getPrice(), countDuplicatesPrice(items, item));
            }
        }
        return prices;
    }

    private Integer countDuplicatesPrice(ArrayList<Item> list, Item id) {
        Integer count = 0;
        for (Item item : list) {
            if(item.getName().equals(id.getName())){
                if (item.getPrice().equals(id.getPrice())) {
                    count++;
                }
            }
        }
        return count;
    }

    private String printPrices(LinkedHashMap<Double, Integer> prices){
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Double, Integer> priceEntry : prices.entrySet()) {
            if (priceEntry.getValue() > 1) {
                builder.append(String.format("Price:   %3.2f       seen: %1d times\n-------------       -------------\n",
                        priceEntry.getKey(), priceEntry.getValue()));
            } else {
                builder.append(String.format("Price:   %3.2f       seen: %1d time\n-------------       -------------\n",
                        priceEntry.getKey(), priceEntry.getValue()));
            }
        }
        return builder.toString();
    }

}





