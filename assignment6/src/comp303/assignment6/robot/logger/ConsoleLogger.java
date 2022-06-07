package comp303.assignment6.robot.logger;

import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.comands.Command;

public class ConsoleLogger implements RobotLogger {
    /**
    Prints the action performed to the console.
    */
    @Override
    public void logRobotState(Command command, Robot robot) {
        System.out.printf("%s action performed, battery level is %d\n", command.toCommandString(), robot.getBatteryCharge());
    }
}
