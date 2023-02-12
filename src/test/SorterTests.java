package test;

import main.helpers.Sorter;
import main.model.AggregateState;
import main.model.Coffee;
import main.model.CoffeeStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SorterTests {
    private ArrayList<Coffee> coffeesToSort;
    private Coffee testCoffee1;
    private Coffee testCoffee2;
    private Coffee testCoffee3;

    @Before
    public void setUp() {
        coffeesToSort = new ArrayList<>();
        testCoffee1 = new Coffee("Test1", AggregateState.cereal, 10.45F, 100F, 300F, 75,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 75);
        // Correlation - 3
        testCoffee2 = new Coffee("Test2", AggregateState.cereal, 10.45F, 100F, 600F, 90,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 90);
        // Correlation - 6
        testCoffee3 = new Coffee("Test3", AggregateState.cereal, 10.45F, 100F, 400F, 67,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 67);
        // Correlation - 4
        coffeesToSort.add(testCoffee1);
        coffeesToSort.add(testCoffee2);
        coffeesToSort.add(testCoffee3);
    }

    @Test
    public void testSortByQuality() {
        Sorter.sortByCoffeeQuality(coffeesToSort);

        // expected: 67, 75, 90
        String expectedOrder = testCoffee3.getName() + testCoffee1.getName() + testCoffee2.getName();
        String methodResult = "";
        for (Coffee coffee: coffeesToSort) {
            methodResult += coffee.getName();
        }
        assertEquals(expectedOrder, methodResult);
    }

    @Test
    public void testSortByCorrelation() {
        Sorter.sortByCoffeeCorrelation(coffeesToSort);

        // expected: 3, 4, 6
        String expectedOrder = testCoffee1.getName() + testCoffee3.getName() + testCoffee2.getName();
        String methodResult = "";
        for (Coffee coffee: coffeesToSort) {
            methodResult += coffee.getName();
        }
        assertEquals(expectedOrder, methodResult);
    }
}
