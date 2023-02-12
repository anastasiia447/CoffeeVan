package main.commands.coffee.search;

import main.commands.Command;
import main.Manager;

public class PrintFoundCoffeeCommand extends Command {
    public PrintFoundCoffeeCommand(Manager manager) {
        super(manager);
    }

    @Override
    public void execute() {
        manager.getFoundCoffees();
    }
}
