package test;

import main.Manager;
import main.helpers.AppLogger;
import main.model.*;
import org.junit.Before;
import org.junit.Test;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ManagerTests {
    Manager manager;

    @Before
    public void setUp() {
        new AppLogger();
        manager = new Manager();
    }

    @Test
    public void testAddVan() {
        int expectedVanSize = manager.getVans().size();
        manager.addVan(new Van(5));
        expectedVanSize++;
        assertEquals(expectedVanSize, manager.getVans().size());
    }

    @Test
    public void testChangeVan() {
        manager.getVans().removeAll(manager.getVans());
        Van expectedVan = new Van(5);
        manager.addVan(expectedVan);

        manager.changeVan(expectedVan.getId(), new Van(3));
        assertEquals(String.valueOf(3.0), String.valueOf(expectedVan.getCapacity()));
    }

    @Test
    public void testDeleteVan() {
        Van vanToDelete = new Van(10);
        int expectedVanSize = manager.getVans().size();
        manager.addVan(vanToDelete);
        manager.deleteVan(vanToDelete.getId());
        assertEquals(expectedVanSize, manager.getVans().size());
    }

    @Test
    public void testLoadVan() {
        Van vanToLoad = new Van(5);
        manager.addVan(vanToLoad);
        // Size of ArrayList<CoffeeGoods> in the van
        int unexpectedSize = 0;
        manager.loadVan(vanToLoad.getId());
        assertNotEquals(unexpectedSize, vanToLoad.getCoffeeGoods().size());
    }

    @Test
    public void testAddCoffee() {
        int expectedArraySize = manager.getCoffees().size() + 1;
        Coffee coffeeToAdd = new Coffee("Test1", AggregateState.cereal, 10.45F, 150.00F, 399.99F, 75,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 75);
        manager.addCoffee(coffeeToAdd);
        assertEquals(expectedArraySize, manager.getCoffees().size());
    }

    @Test
    public void testChangeCoffee() {
        Coffee coffeeToChange = new Coffee("Test1", AggregateState.cereal, 10.45F, 150.00F, 399.99F, 75,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 75);
        Coffee changedCoffee = new Coffee("Test2", AggregateState.cereal, 10.45F, 150.00F, 399.99F, 75,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 75);
        manager.addCoffee(coffeeToChange);
        manager.changeCoffee(coffeeToChange.getId(), changedCoffee);
        assertEquals(coffeeToChange.getName(), changedCoffee.getName());
    }

    @Test
    public void testDeleteCoffee() {
        int expectedArraySize = manager.getCoffees().size() ;
        Coffee coffeeToDelete = new Coffee("Test1", AggregateState.cereal, 10.45F, 150.00F, 399.99F, 75,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 75);
        manager.addCoffee(coffeeToDelete);
        manager.deleteCoffee(coffeeToDelete.getId());
        assertEquals(expectedArraySize, manager.getCoffees().size());
    }
}
