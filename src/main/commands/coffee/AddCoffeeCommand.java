package main.commands.coffee;

import main.commands.Command;
import main.Manager;
import main.model.AggregateState;
import main.model.Coffee;
import main.model.CoffeeStatus;

import static main.helpers.Extension.*;

public class AddCoffeeCommand extends Command {
    private final String name;
    private final AggregateState aggregateState;
    private final float volume;
    private final float nettoWeight;
    private final float price;
    private int qualityMark;
    private CoffeeStatus coffeeStatus;
    private final String cultivationPlace;
    private final String transportationCompany;
    private final int cappingMark;

    public AddCoffeeCommand(String name, String aggregateState,
                            String volume, String nettoWeight,
                            String price, String qualityMark,
                            String coffeeStatus,
                            String cultivationPlace,
                            String transportationCompany, String cappingMark,
                            Manager manager) {
        super(manager);
        this.name = name;
        this.aggregateState = parseAggregateState(aggregateState);
        this.coffeeStatus = parseCoffeeStatus(coffeeStatus);
        this.cultivationPlace = cultivationPlace;
        this.transportationCompany = transportationCompany;
        this.volume = parseFloat(volume);
        this.nettoWeight = parseFloat(nettoWeight);
        this.price = parseFloat(price);
        this.qualityMark = parseInt(qualityMark);
        this.cappingMark = parseInt(cappingMark);

        if (this.qualityMark == 0) {
            this.qualityMark = Coffee.calculateQuality(this.cappingMark, this.cultivationPlace, this.transportationCompany);
        }

        if (this.coffeeStatus == null) {
            this.coffeeStatus = Coffee.defineQualityStatus(this.qualityMark, this.cultivationPlace);
        }
    }

    @Override
    public void execute() {
        manager.addCoffee(new Coffee(
                name, aggregateState, volume,
                nettoWeight, price, qualityMark,
                coffeeStatus, cultivationPlace,
                transportationCompany,
                cappingMark
        ));
    }
}
