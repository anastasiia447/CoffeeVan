package test;

import main.helpers.Searcher;
import main.model.AggregateState;
import main.model.Coffee;
import main.model.CoffeeStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class SearcherTests {
    private ArrayList<Coffee> coffeeArrayList;

    @Before
    public void setUp() {
        coffeeArrayList = new ArrayList<>();
        Coffee testCoffee1 = new Coffee("Test1", AggregateState.solubleInCans, 10.45F, 100F, 300F, 75,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 75);
        // Correlation - 3
        Coffee testCoffee2 = new Coffee("Test2", AggregateState.cereal, 10.45F, 100F, 600F, 90,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 90);
        // Correlation - 6
        Coffee testCoffee3 = new Coffee("Test3", AggregateState.ground, 10.45F, 100F, 400F, 67,
                CoffeeStatus.higher, "Brazil Brazil none", "GGS", 67);
        // Correlation - 4
        coffeeArrayList.add(testCoffee1);
        coffeeArrayList.add(testCoffee2);
        coffeeArrayList.add(testCoffee3);
    }

    // Find by name
    @Test
    public void testFindCoffeeByName() {
        String expectedName = "Test2";
        ArrayList<Coffee> methodResult = Searcher.findCoffee(coffeeArrayList, expectedName);
        assertEquals(expectedName, methodResult.get(0).getName());
    }

    // Find by name & aggregate state
    @Test
    public void testFindCoffee() {
        String expectedName = "Test3";
        AggregateState expectedState = AggregateState.ground;

        ArrayList<Coffee> methodResult = Searcher.findCoffee(coffeeArrayList, expectedName, expectedState);

        assertEquals(expectedName, methodResult.get(0).getName());
        assertEquals(expectedState, methodResult.get(0).getAggregateState());
    }

    // Find by quality mark
    @Test
    public void testFindCoffeeByQuality() {
        int expectedQualityMark = 67;

        ArrayList<Coffee> methodResult = Searcher.findCoffee(coffeeArrayList, expectedQualityMark);

        assertEquals(expectedQualityMark, methodResult.get(0).getQualityMark());
    }

    // Find by range of quality mark
    @Test
    public void testFindCoffeeByRangeQuality() {
        int minValue = 0;
        int maxValue = 80;

        ArrayList<Coffee> methodResult = Searcher.findCoffee(coffeeArrayList, minValue, maxValue);

        assertEquals(coffeeArrayList.get(0), methodResult.get(0));
        assertEquals(coffeeArrayList.get(2), methodResult.get(1));
    }

    // Find by range of correlation
    @Test
    public void testFindCoffeeByRangeCorr() {
        float minValue = 5;
        float maxValue = 10;

        ArrayList<Coffee> methodResult = Searcher.findCoffee(coffeeArrayList, minValue, maxValue);

        assertEquals(coffeeArrayList.get(1), methodResult.get(0));
    }
}
