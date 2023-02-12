package test;

import main.helpers.AppLogger;
import main.helpers.FileManager;
import main.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileManagerTest {
    ArrayList<Coffee> readiedCoffee;
    ArrayList<Van> readiedVans;
    ArrayList<CoffeeGoods> readiedGoods;

    @Before
    public void setUp() {
        new AppLogger();
        readiedCoffee = new ArrayList<>();
        readiedVans = new ArrayList<>();

        var coffee = new Coffee("Test1", AggregateState.cereal, 10.45F, 100F, 300F, 75,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 75);
        var van = new Van(5);
        van.loadVan(readiedCoffee);

        readiedCoffee.add(coffee);
        readiedVans.add(van);
    }

    @Test
    public void saveCoffees() {
        boolean flag = FileManager.saveCoffeeData(readiedCoffee);
        assertTrue(flag);
    }

    @Test
    public void saveVans() {
        boolean flag = FileManager.saveVansData(readiedVans);
        assertTrue(flag);
    }

    @Test
    public void saveGoods() {
        boolean flag = FileManager.saveVansGoods(readiedVans);
        assertTrue(flag);
    }

    @Test
    public void readCoffees() {
        readiedCoffee = FileManager.readCoffee();
        assertNotEquals(0, readiedCoffee.size());
    }

    @Test
    public void readVans() {
        readiedVans = FileManager.readVans();
        assertNotEquals(0, readiedVans.size());
    }
}
