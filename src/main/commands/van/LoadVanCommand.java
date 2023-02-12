package main.commands.van;

import main.commands.Command;
import main.Manager;

import java.util.UUID;

import static main.helpers.Extension.parseId;

public class LoadVanCommand extends Command {
    private final UUID id;

    public LoadVanCommand(String id, Manager manager) {
        super(manager);
        this.id = parseId(id);
    }

    @Override
    public void execute() {
        manager.loadVan(id);
    }
}
