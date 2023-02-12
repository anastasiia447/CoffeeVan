package main.helpers;

import main.model.AggregateState;
import main.model.Coffee;

import java.util.ArrayList;
import java.util.Objects;

public final class Searcher {
    // Find coffees by coffee name
    public static ArrayList<Coffee> findCoffee(ArrayList<Coffee> coffees, String name) {
        ArrayList<Coffee> resultArray = new ArrayList<>();
        for (Coffee coffee : coffees) {
            if (Objects.equals(coffee.getName(), name)) {
                resultArray.add(coffee);
            }
        }

        return resultArray;
    }

    // Find coffees by coffee name & aggregate state
    public static ArrayList<Coffee> findCoffee(ArrayList<Coffee> coffees, String name, AggregateState state) {
        ArrayList<Coffee> resultArray = new ArrayList<>();
        for (Coffee coffee : coffees) {
            if (Objects.equals(coffee.getName(), name)) {
                if (Objects.equals(coffee.getAggregateState(), state)) {
                    resultArray.add(coffee);
                }
            }
        }
        return resultArray;
    }

    // Find coffees by coffee quality mark
    public static ArrayList<Coffee> findCoffee(ArrayList<Coffee> coffees, int quality) {
        ArrayList<Coffee> resultArray = new ArrayList<>();
        for (Coffee coffee : coffees) {
            if (Objects.equals(coffee.getQualityMark(), quality)) {
                resultArray.add(coffee);
            }
        }

        return resultArray;
    }


    // Find coffees by coffee quality mark in range(minMark, maxMark)
    public static ArrayList<Coffee> findCoffee(ArrayList<Coffee> coffees, int minQuality, int maxQuality) {
        ArrayList<Coffee> resultArray = new ArrayList<>();
        for (Coffee coffee : coffees) {
            if (minQuality < coffee.getQualityMark() && coffee.getQualityMark() < maxQuality) {
                resultArray.add(coffee);
            }
        }
        return resultArray;
    }


    // Find coffees by correlation price to quality in range(minCorr, maxCorr)
    public static ArrayList<Coffee> findCoffee(ArrayList<Coffee> coffees, float minCorr, float maxCorr) {
        ArrayList<Coffee> resultArray = new ArrayList<>();
        for (Coffee coffee : coffees) {
            float corr = coffee.getPrice() / coffee.getNettoWeight();
            if (minCorr < corr && corr < maxCorr) {
                resultArray.add(coffee);
            }
        }
        return resultArray;
    }
}
