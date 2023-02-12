package main.model;

import java.util.UUID;

public record CoffeeGoods(UUID vanId, UUID coffeeId, int number) {
    @Override
    public String toString() {
        return "Coffee id: " + coffeeId + "\n\t" +
                "Number: " + number;
    }

    public String toFileType() {
        return vanId + " " + coffeeId + " " + number + "\n";
    }
}