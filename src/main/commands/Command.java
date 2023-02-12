package main.commands;

import main.Manager;

public abstract class Command {
    protected final Manager manager;

    protected Command(Manager manager){
        this.manager = manager;
    }

    public abstract void execute();
}
