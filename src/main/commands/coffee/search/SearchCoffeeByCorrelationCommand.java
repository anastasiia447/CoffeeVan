package main.commands.coffee.search;

import main.commands.Command;
import main.Manager;

import static main.helpers.Extension.parseFloat;

public class SearchCoffeeByCorrelationCommand extends Command {
    private final float minCorrelation;
    private final float maxCorrelation;

    public SearchCoffeeByCorrelationCommand(String minCorrelation, String maxCorrelation, Manager manager) {
        super(manager);
        this.minCorrelation = parseFloat(minCorrelation);
        this.maxCorrelation = parseFloat(maxCorrelation);
    }

    @Override
    public void execute() {
        manager.findCoffee(minCorrelation, maxCorrelation);
    }
}
