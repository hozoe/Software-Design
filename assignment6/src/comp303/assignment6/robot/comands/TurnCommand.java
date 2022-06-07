package comp303.assignment6.robot.comands;

import comp303.assignment6.robot.Robot;

public class TurnCommand extends Command {

    private int direction;

    public TurnCommand(int direction) {
        super("Turn");
        if (direction == 1 || direction == -1) {
            this.direction = direction;
        } else {
            throw new IllegalArgumentException("direction can be only 1 or -1");
        }
    }


    @Override
    public void execute(Robot robot) {
        assert robot.getArmState() == Robot.ArmState.RETRACTED;
        robot.turnRobot(direction * 90);
    }

    @Override
    public String toCommandString() {
        String dirStr = "left";
        if (direction == 1) {
            dirStr = "right";
        }
        return String.format("%s %s", name, dirStr);
    }


}
