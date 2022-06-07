package comp303.assignment6.robot.comands;

import comp303.assignment6.robot.Robot;

public class MoveForwardCommand extends Command {

    private double distance;

    public MoveForwardCommand(double distance) {
        super("Move");
        this.distance = distance;
    }


    @Override
    public void execute(Robot robot) {
        assert robot.getArmState()== Robot.ArmState.RETRACTED;
        robot.moveRobot(distance);

    }

    @Override
    public String toCommandString() {
        return String.format("%s %f", name, distance);
    }
}
