package main.helpers;

import main.Settings;
import main.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class FileManager {
    public static ArrayList<Coffee> readCoffee() {
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(Settings.coffeesFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            AppLogger.logger.warning("IOEXCEPTION! " + e);
            return new ArrayList<>();
        }

        ArrayList<Coffee> array = new ArrayList<>();

        try {
            FileReader reader = new FileReader(Settings.coffeesFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            for (int i = 0; i < lines.size(); i++) {
                String[] data = bufferedReader.readLine().split(" ");

                Coffee coffee = new Coffee(
                        UUID.fromString(data[0]), // ID
                        data[1], // NAME
                        AggregateState.valueOf(data[2]), // AGGREGATE STATE
                        Float.parseFloat(data[3]), // VOLUME
                        Float.parseFloat(data[4]), // NETTO WEIGHT
                        Float.parseFloat(data[5]), // PRICE
                        Integer.parseInt(data[6]), // QUALITY MARK
                        CoffeeStatus.valueOf(data[7]), // COFFEE STATUS
                        new CultivationPlace(UUID.fromString(data[0]), data[8], data[9], data[10]), // CULTIVATION PLACE
                        data[11], // TRANSPORTATION COMPANY
                        Integer.parseInt(data[12]) // CAPPING MARK
                );

                array.add(coffee);
            }


        } catch (IOException e) {
            AppLogger.logger.warning("IOEXCEPTION! " + e);
            return new ArrayList<>();
        }

        return array;
    }

    public static ArrayList<Van> readVans() {
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(Settings.vansFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            AppLogger.logger.warning("IOEXCEPTION! " + e);
            return new ArrayList<>();
        }

        ArrayList<Van> array = new ArrayList<>();

        try {
            FileReader reader = new FileReader(Settings.vansFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            for (int i = 0; i < lines.size(); i++) {
                String[] data = bufferedReader.readLine().split(" ");

                Van van = new Van(
                        UUID.fromString(data[0]), // ID
                        Float.parseFloat(data[1]) // CAPACITY
                );

                array.add(van);
            }


        } catch (IOException e) {
            AppLogger.logger.warning("IOEXCEPTION! " + e);
            return new ArrayList<>();
        }

        return array;
    }

    public static ArrayList<CoffeeGoods> readVanGoods() {
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(Settings.vanGoodsFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            AppLogger.logger.warning("IOEXCEPTION! " + e);
            return new ArrayList<>();
        }

        ArrayList<CoffeeGoods> array = new ArrayList<>();

        try {
            FileReader reader = new FileReader(Settings.vanGoodsFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            for (int i = 0; i < lines.size(); i++) {
                String[] data = bufferedReader.readLine().split(" ");

                CoffeeGoods goods = new CoffeeGoods(
                        UUID.fromString(data[0]), // Van ID
                        UUID.fromString(data[1]), // Coffee ID
                        Integer.parseInt(data[2]) // Number of goods
                );
                array.add(goods);
            }


        } catch (IOException e) {
            AppLogger.logger.warning("IOEXCEPTION! " + e);
            return new ArrayList<>();
        }

        return array;
    }

    public static void saveData(ArrayList<Coffee> coffees, ArrayList<Van> vans) {
        saveCoffeeData(coffees);
        saveVansData(vans);
        saveVansGoods(vans);
    }

    public static boolean saveCoffeeData(ArrayList<Coffee> coffees) {
        try {
            FileWriter writer = new FileWriter(Settings.coffeesFilePath);

            for (Coffee coffee : coffees) {
                writer.write(coffee.toFileType());
            }

            writer.close();
            return true;
        } catch (IOException e) {
            AppLogger.logger.warning("Cannot save 'CoffeeData'!\n" + e.getMessage());
            return false;
        }
    }

    public static boolean saveVansData(ArrayList<Van> vans) {
        try {
            FileWriter writer = new FileWriter(Settings.vansFilePath);

            for (Van van : vans) {
                writer.write(van.toFileType());
            }

            writer.close();
            return true;
        } catch (IOException e) {
            AppLogger.logger.warning("Cannot save  'VansData'!" + e.getMessage());
            return false;
        }
    }

    public static boolean saveVansGoods(ArrayList<Van> vans) {
        try {
            FileWriter writer = new FileWriter(Settings.vanGoodsFilePath);

            for (Van van : vans) {
                ArrayList<CoffeeGoods> goods = van.getCoffeeGoods();
                if (van.getCoffeeGoods() != null) {
                    for (CoffeeGoods good : goods) {
                        if (good != null) {
                            writer.write(good.toFileType());
                        }
                    }
                }
            }

            writer.close();
            return true;
        } catch (IOException e) {
            AppLogger.logger.warning("Cannot save 'VansGoods'!" + e.getMessage());
            return false;
        }
    }
}
