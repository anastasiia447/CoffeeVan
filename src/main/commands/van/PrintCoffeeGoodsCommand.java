package main.commands.van;

import main.Manager;
import main.commands.Command;
import main.helpers.Extension;

import java.util.UUID;

public class PrintCoffeeGoodsCommand extends Command {
    private final UUID id;

    public PrintCoffeeGoodsCommand(Manager manager, String id) {
        super(manager);
        this.id = Extension.parseId(id);
    }

    @Override
    public void execute() {
        manager.printVanGoods(id);
    }
}
