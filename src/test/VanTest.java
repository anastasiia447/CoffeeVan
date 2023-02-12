package test;
import main.helpers.AppLogger;
import main.model.AggregateState;
import main.model.Coffee;
import main.model.CoffeeStatus;
import main.model.Van;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

public class VanTest {
    private ArrayList<Coffee> coffees;
    private Van van;

    @Before
    public void setUp() {
        new AppLogger();
        coffees = new ArrayList<>();
        var coffee1 = new Coffee("Test1", AggregateState.cereal, 10.45F, 100F, 300F, 67,
                CoffeeStatus.ordinary, "Brazil Brazil none", "GGS", 75);
        var coffee2 = new Coffee("Test2", AggregateState.cereal, 10.45F, 100F, 300F, 65,
                CoffeeStatus.ordinary, "Brazil Brazil none", "GGS", 75);
        var coffee3 = new Coffee("Test3", AggregateState.cereal, 10.45F, 100F, 300F, 75,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 75);
        var coffee4 = new Coffee("Test4", AggregateState.cereal, 10.45F, 100F, 300F, 73,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 75);
        var coffee5 = new Coffee("Test5", AggregateState.cereal, 10.45F, 100F, 300F, 88,
                CoffeeStatus.specialty, "Brazil Brazil none", "GGS", 75);
        var coffee6 = new Coffee("Test6", AggregateState.cereal, 10.45F, 100F, 300F, 90,
                CoffeeStatus.specialty, "Brazil Brazil none", "GGS", 75);

        coffees.add(coffee1);
        coffees.add(coffee2);
        coffees.add(coffee3);
        coffees.add(coffee4);
        coffees.add(coffee5);
        coffees.add(coffee6);

        van = new Van(3);
    }

    @Test
    public void testFillArray() {
        van.loadVan(coffees);
        assertNotEquals(0, van.getCoffeeGoods().size());
    }
}
