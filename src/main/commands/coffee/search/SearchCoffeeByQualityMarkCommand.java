package main.commands.coffee.search;

import main.commands.Command;
import main.Manager;

import static main.helpers.Extension.parseInt;

public class SearchCoffeeByQualityMarkCommand extends Command {
    private final int quality;

    public SearchCoffeeByQualityMarkCommand(String qualityMark, Manager manager) {
        super(manager);
        this.quality = parseInt(qualityMark);
    }

    @Override
    public void execute() {
        manager.findCoffee(quality);
    }
}
