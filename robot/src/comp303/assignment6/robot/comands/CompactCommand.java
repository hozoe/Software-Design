package comp303.assignment6.robot.comands;

import comp303.assignment6.robot.Robot;

public class CompactCommand extends Command {


    public CompactCommand() {
        super("Compact");
    }


    @Override
    public void execute(Robot robot) {
        assert robot.getGripperState()== Robot.GripperState.HOLDING_OBJECT;
        robot.compact();
    }

    @Override
    public String toCommandString() {
        return name;
    }
}
