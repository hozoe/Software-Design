package comp303.assignment6.robot.comands;

import comp303.assignment6.robot.Robot;

public class GrabCommand extends Command{
    public GrabCommand() {
        super("Grab");
    }

    @Override
    public void execute(Robot robot) {
        robot.extendArm();
        robot.closeGripper();
        robot.retractArm();
    }

    @Override
    public String toCommandString() {
        return name;
    }

}
