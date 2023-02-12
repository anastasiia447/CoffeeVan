package main.commands.van;

import main.commands.Command;
import main.Manager;
import main.model.Van;

import static main.helpers.Extension.parseFloat;

public class AddVanCommand extends Command {
    private float capacity;

    public AddVanCommand(String capacity, Manager manager) {
        super(manager);
        this.capacity = parseFloat(capacity);
    }

    @Override
    public void execute() {
        manager.addVan(new Van(capacity));
    }
}
