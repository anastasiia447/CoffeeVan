package main.commands.coffee.search;

import main.commands.Command;
import main.Manager;
import main.model.AggregateState;

import static main.helpers.Extension.parseAggregateState;

public class SearchCoffeeByAggregateStateCommand extends Command {
    private final String name;
    private final AggregateState state;

    public SearchCoffeeByAggregateStateCommand(String name, String aggregateState, Manager manager) {
        super(manager);
        this.name = name;
        this.state = parseAggregateState(aggregateState);
    }

    @Override
    public void execute() {
        manager.findCoffee(name, state);
    }
}
