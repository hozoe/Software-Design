package comp303.assignment6.robot.comands;

import comp303.assignment6.robot.Robot;

public class ReleaseCommand extends Command {
    public ReleaseCommand() {
        super("Release");
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
