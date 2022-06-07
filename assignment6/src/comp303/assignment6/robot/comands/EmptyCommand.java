package comp303.assignment6.robot.comands;

import comp303.assignment6.robot.Robot;

public class EmptyCommand extends Command {


    public EmptyCommand() {
        super("Empty");
    }


    @Override
    public void execute(Robot robot) {
        robot.emptyCompactor();
    }

    @Override
    public String toCommandString() {
        return name;
    }
}
