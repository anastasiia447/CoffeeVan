package main.commands;

import main.Manager;

public class SaveDataCommand extends Command{
    public SaveDataCommand(Manager manager) {
        super(manager);
    }

    @Override
    public void execute() {
        manager.saveData();
    }
}
