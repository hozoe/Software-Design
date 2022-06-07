package comp303.assignment6.robot.comands;

//package comp303.assignment6.robot.commands;

import comp303.assignment6.robot.Robot;

public abstract class Command {

    protected String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute(Robot robot);

    public abstract String toCommandString();
}
