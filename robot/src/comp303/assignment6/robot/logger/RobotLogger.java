package comp303.assignment6.robot.logger;

import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.comands.Command;

public interface RobotLogger {
    void logRobotState(Command command, Robot robot);
}
