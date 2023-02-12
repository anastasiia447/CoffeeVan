package main.model;

import java.util.UUID;

public class CultivationPlace {
    private UUID id;
    private UUID coffeeId;
    private String country;
    private String state;
    private String farm;

    public CultivationPlace(UUID coffeeId){
        this.id = UUID.randomUUID();
        this.coffeeId = coffeeId;
        this.country = "none";
        this.state = "none";
        this.farm = "none";
    }

    public CultivationPlace(UUID coffeeId, String country, String state, String farm){
        this.id = UUID.randomUUID();
        this.coffeeId = coffeeId;
        this.country = country;
        this.state = state;
        this.farm = farm;
    }

    // Copy Constructor
    CultivationPlace(UUID coffeeId, CultivationPlace place){
        this(coffeeId);
        this.country = place.getCountry();
        this.state = place.getState();
        this.farm = place.getFarm();
    }

    @Override
    public String toString() {
        return "Cultivation country: " + country + " state: " + state + " farm: " + farm;
    }

    // Getters & Setters
    public UUID getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getFarm() {
        return farm;
    }
}
