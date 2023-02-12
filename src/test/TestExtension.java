package test;

import main.helpers.AppLogger;
import main.model.AggregateState;
import main.model.CoffeeStatus;
import main.model.CultivationPlace;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static main.helpers.Extension.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestExtension {
    UUID id;
    String stringId;
    String string;
    CultivationPlace place;

    @Before
    public void setUp() {
        new AppLogger();
        id = UUID.randomUUID();
        stringId = id.toString();
        string = "Brazil";

        place = new CultivationPlace(id, string, string, string);
    }

    @Test
    public void testParseId() {
        assertEquals(id, parseId(stringId));
        assertNull(parseId(string));
    }

    @Test
    public void parseAggregateStateTest() {
        assertEquals(AggregateState.cereal, parseAggregateState("cereal"));
        assertNull(parseAggregateState(string));
    }

    @Test
    public void parseCoffeeStatusTest() {
        assertEquals(CoffeeStatus.ordinary, parseCoffeeStatus("ordinary"));
        assertEquals(CoffeeStatus.higher, parseCoffeeStatus("higher"));
        assertEquals(CoffeeStatus.specialty, parseCoffeeStatus("specialty"));
        assertNull(parseCoffeeStatus(string));
    }

    @Test
    public void parseCultivationPlaceTest() {
        assertEquals(place.getCountry(), parseCultivationPlace(id, "Brazil Brazil Brazil").getCountry());
        assertEquals(place.getState(), parseCultivationPlace(id, "Brazil Brazil Brazil").getState());
        assertEquals(place.getFarm(), parseCultivationPlace(id, "Brazil Brazil Brazil").getFarm());
        assertNull(parseCultivationPlace(null, string));
    }
}
