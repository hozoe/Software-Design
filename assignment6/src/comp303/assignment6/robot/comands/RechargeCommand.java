package comp303.assignment6.robot.comands;

import comp303.assignment6.robot.Robot;

public class RechargeCommand extends Command {


    public RechargeCommand() {
        super("Recharge");
    }


    @Override
    public void execute(Robot robot) {
        robot.rechargeBattery();
    }

    @Override
    public String toCommandString() {
        return name;
    }

}
