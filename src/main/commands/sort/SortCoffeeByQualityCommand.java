package main.commands.sort;

import main.commands.Command;
import main.Manager;

public class SortCoffeeByQualityCommand extends Command {
    public SortCoffeeByQualityCommand(Manager manager) {
        super(manager);
    }

    @Override
    public void execute() {
        manager.sortCoffeeByQuality();
    }
}
