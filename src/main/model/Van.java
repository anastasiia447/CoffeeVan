package main.model;

import main.Settings;

import java.util.ArrayList;
import java.util.UUID;

import static main.helpers.AppLogger.logger;

public class Van {
    private UUID id;
    private float capacity; //m^3
    private ArrayList<CoffeeGoods> coffeeGoods;

    public Van(float capacity) {
        id = UUID.randomUUID();
        this.capacity = capacity;
        this.coffeeGoods = null;
    }

    public Van(Van van) {
        this.id = van.getId();
        this.capacity = van.getCapacity();
        this.coffeeGoods = van.getCoffeeGoods();
    }

    // Constructor for the file manager
    public Van(UUID id, float capacity) {
        this.id = id;
        this.capacity = capacity;
        this.coffeeGoods = null;
    }

    @Override
    public String toString() {
        String res = "Van: " + id + "\n\t" +
                "Capacity: " + capacity + "\n\t" +
                "Is empty: ";
        if (coffeeGoods == null) {
            return res + "true";
        } else return res + "false";
    }

    public void loadVanByAdditionalGoods(ArrayList<CoffeeGoods> goods) {
        // If coffeeGoods initialize...
        if (coffeeGoods != null) {
            // Add elements to the exist data
            coffeeGoods.addAll(goods);
        } else {
            // else rea-sign coffeeGoods
            coffeeGoods = goods;
        }
    }

    public void setData(Van van) {
        this.capacity = van.getCapacity();
    }

    public UUID getId() {
        return id;
    }

    public float getCapacity() {
        return capacity;
    }

    public ArrayList<CoffeeGoods> getCoffeeGoods() {
        return coffeeGoods;
    }

    // TODO: 27.01.2023 IMPLEMENT METHOD
    public void printCoffeeGoods() {
        if (coffeeGoods != null) {
            for (CoffeeGoods good : coffeeGoods) {
                System.out.println(good.toString());
            }
        } else {
            System.out.println("Van is empty!");
        }
    }

    public void loadVan(ArrayList<Coffee> coffeeArray) {
        logger.info("load van executed");
        // Convert m^3 in sm^3
        float ordinaryMaxVolume = this.capacity * 100000 * Settings.ordinaryQualityCoeff;
        float higherMaxVolume = this.capacity * 100000 * Settings.higherQualityCoeff;
        float specialtyMaxVolume = this.capacity * 100000 * Settings.specialtyQualityCoeff;

        // Fill the arrays with the same CoffeeStatus types
        ArrayList<Coffee> ordinaryCoffee = fillArray(coffeeArray, CoffeeStatus.ordinary);
        ArrayList<Coffee> higherCoffee = fillArray(coffeeArray, CoffeeStatus.higher);
        ArrayList<Coffee> specialtyCoffee = fillArray(coffeeArray, CoffeeStatus.specialty);

        // Initialize coffeeGoods
        coffeeGoods = new ArrayList<>();

        // Choose goods to the van
        chooseGoods(ordinaryCoffee, ordinaryMaxVolume);
        chooseGoods(higherCoffee, higherMaxVolume);
        chooseGoods(specialtyCoffee, specialtyMaxVolume);
    }

    private void chooseGoods(ArrayList<Coffee> coffee, float volume) {
        // Foreach goods...
        for (int i = 0; i < coffee.size(); i++) {
            // Count available space for this goods
            float currentVolume = volume / coffee.size() - i;
            // Integer num to count number of this goods
            int currNumber = (int) (currentVolume / coffee.get(i).getVolume());
            // Plus remained volume to general volume
            volume += (currentVolume % coffee.get(i).getVolume());

            // write down new data to array
            coffeeGoods.add(new CoffeeGoods(this.id, coffee.get(i).getId(), currNumber));
        }
    }

    // Filling new array with the same coffee status
    private ArrayList<Coffee> fillArray(ArrayList<Coffee> coffeeArray, CoffeeStatus status) {
        ArrayList<Coffee> temp = new ArrayList<>();
        for (Coffee coffee : coffeeArray) {
            if (coffee.getCoffeeStatus() == status) {
                temp.add(coffee);
            }
        }
        return temp;
    }

    public String toFileType() {
        return id + " " + capacity + "\n";
    }
}