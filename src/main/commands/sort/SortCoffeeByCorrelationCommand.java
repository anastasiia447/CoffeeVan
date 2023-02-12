package main.commands.sort;

import main.commands.Command;
import main.Manager;

public class SortCoffeeByCorrelationCommand extends Command {
    public SortCoffeeByCorrelationCommand(Manager manager) {
        super(manager);
    }

    @Override
    public void execute() {
        manager.sortCoffeeByCorrelation();
    }
}
