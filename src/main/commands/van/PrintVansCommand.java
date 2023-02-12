package main.commands.van;

import main.commands.Command;
import main.Manager;

public class PrintVansCommand extends Command {
    public PrintVansCommand(Manager manager) {
        super(manager);
    }

    @Override
    public void execute() {
        manager.printVans();
    }
}
