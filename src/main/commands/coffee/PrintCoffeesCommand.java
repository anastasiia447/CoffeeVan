package main.commands.coffee;

import main.commands.Command;
import main.Manager;

public class PrintCoffeesCommand extends Command {
    public PrintCoffeesCommand(Manager manager) {
        super(manager);
    }

    @Override
    public void execute() {
        manager.printCoffees();
    }
}
