package main.model;

import main.helpers.Extension;

import java.util.Objects;
import java.util.UUID;

import static main.helpers.Extension.parseCultivationPlace;

public class Coffee {
    private final UUID id;
    private String name;
    private AggregateState aggregateState;
    private float volume;
    private float nettoWeight;
    private float price;
    private int qualityMark;
    private CoffeeStatus coffeeStatus;
    private CultivationPlace cultivationPlace;
    private String transportationCompany;
    private int cappingMark;

    public Coffee(String name, AggregateState aggregateState, float volume, float nettoWeight, float price,
                  int qualityMark, CoffeeStatus coffeeStatus, String cultivationPlace,
                  String transportationCompany, int cappingMark) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.aggregateState = aggregateState;
        this.volume = volume;
        this.nettoWeight = nettoWeight;
        this.price = price;
        this.qualityMark = qualityMark;
        this.coffeeStatus = coffeeStatus;
        this.cultivationPlace = parseCultivationPlace(id, cultivationPlace);
        this.transportationCompany = transportationCompany;
        this.cappingMark = cappingMark;
    }

    // Copy Constructor
    public Coffee(Coffee coffee) {
        this.id = coffee.getId();
        this.name = coffee.getName();
        this.aggregateState = coffee.getAggregateState();
        this.volume = coffee.getVolume();
        this.nettoWeight = coffee.getNettoWeight();
        this.price = coffee.getPrice();
        this.qualityMark = coffee.getQualityMark();
        this.coffeeStatus = coffee.getCoffeeStatus();
        this.cultivationPlace = new CultivationPlace(id, coffee.getCultivationPlace());
        this.transportationCompany = coffee.getTransportationCompany();
        this.cappingMark = coffee.getCappingMark();
    }

    // Constructor for the file reader
    public Coffee(UUID id, String name,
                  AggregateState aggregateState,
                  float volume, float nettoWeight,
                  float price, int qualityMark,
                  CoffeeStatus status, CultivationPlace place,
                  String transportationCompany, int cappingMark) {
        this.id = id;
        this.name = name;
        this.aggregateState = aggregateState;
        this.volume = volume;
        this.nettoWeight = nettoWeight;
        this.price = price;
        this.qualityMark = qualityMark;
        this.coffeeStatus = status;
        this.cultivationPlace = place;
        this.transportationCompany = transportationCompany;
        this.cappingMark = cappingMark;
    }

    public static int calculateQuality(int cappingMark, String cultivationPlace, String transportationCompany) {
        int mark;
        CultivationPlace place = Extension.parseCultivationPlace(UUID.randomUUID(), cultivationPlace);
        // if coffee hasn't capping mark
        if (cappingMark == 0) {

            // then automatically define quality mark
            mark = 20;
            if (!Objects.equals(place.getCountry(), "none")) {
                if (!Objects.equals(place.getState(), "none")) {
                    if (!Objects.equals(place.getFarm(), "none")) {
                        mark += 40;
                    } else mark += 20;
                } else mark += 10;
            }
            if (!Objects.equals(transportationCompany, "none")) {
                mark += 10;
            }
            // max quality mark without capping process is 70
        } else {
            mark = cappingMark;
        }
        return mark;
    }

    public static CoffeeStatus defineQualityStatus(int qualityMark, String place) {
        CultivationPlace cultivationPlace = parseCultivationPlace(UUID.randomUUID(), place);
        if (qualityMark < 60) {
            return CoffeeStatus.ordinary;
        } else if (qualityMark < 80) {
            return CoffeeStatus.higher;
        } else {
            if (!Objects.equals(cultivationPlace.getCountry(), "none") &&
                    !Objects.equals(cultivationPlace.getState(), "none")) {
                return CoffeeStatus.specialty;
            } else {
                return CoffeeStatus.higher;
            }
        }
    }

    @Override
    public String toString() {
        return "Coffee: " + name + " UUID: " + id + "\n\t" +
                "Aggregate state: " + aggregateState.toString() + "; " + "volume: " + volume + ";\n\t" +
                "netto weight: " + nettoWeight + ";\n\t" +
                "quality mark: " + qualityMark + "; " +  "coffee status: " + coffeeStatus + "; " +
                "capping mark: " + cappingMark + ";\n\t" +
                cultivationPlace.toString() + ";\n\t" +
                "transportation company: " + transportationCompany + ";\n\t" +
                "PRICE: " + price + ";";
    }

    /* Getters
     * &
     * Setters */

    public void setData(Coffee coffee) {
        this.name = coffee.getName();
        this.aggregateState = coffee.aggregateState;
        this.volume = coffee.getVolume();
        this.nettoWeight = coffee.getNettoWeight();
        this.price = coffee.getPrice();
        this.cappingMark = coffee.getCappingMark();
        this.cultivationPlace = coffee.getCultivationPlace();
        this.transportationCompany = coffee.transportationCompany;
        this.qualityMark = coffee.getQualityMark();
        this.coffeeStatus = coffee.getCoffeeStatus();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AggregateState getAggregateState() {
        return aggregateState;
    }

    public float getVolume() {
        return volume;
    }

    public float getNettoWeight() {
        return nettoWeight;
    }

    public float getPrice() {
        return price;
    }

    public int getQualityMark() {
        return qualityMark;
    }

    public CoffeeStatus getCoffeeStatus() {
        return coffeeStatus;
    }

    public CultivationPlace getCultivationPlace() {
        return cultivationPlace;
    }

    public String getTransportationCompany() {
        return transportationCompany;
    }

    public int getCappingMark() {
        return cappingMark;
    }

    public boolean isNull() {
        if (id != null) {
            if (aggregateState != null && coffeeStatus != null && cultivationPlace != null) {
                return false;
            }
        }
        return true;
    }

    public String toFileType() {
        return id + " " + name + " " + aggregateState + " " +
                volume + " " + nettoWeight + " " + price + " " +
                qualityMark + " " + coffeeStatus + " " +
                cultivationPlace.getCountry() + " " +
                cultivationPlace.getState() + " " +
                cultivationPlace.getFarm() + " " +
                transportationCompany + " " +
                cappingMark + "\n";
    }
}
