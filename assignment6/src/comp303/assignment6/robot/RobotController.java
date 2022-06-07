package comp303.assignment6.robot;

import comp303.assignment6.robot.comands.Command;
import comp303.assignment6.robot.logger.RobotLogger;

public class RobotController {

    private RobotLogger robotLogger;
    protected Robot robot;

    public RobotController(Robot robot, RobotLogger robotLogger) {
        this.robot = robot;
        this.robotLogger = robotLogger;
    }

    /**
     * Recharges battery when needed, then execute this single command.
     * Logging this command to robotLogger.
     * @param command
     */
    public void execute(Command command) {
        if (robot.getBatteryCharge() <= 5) {
            robot.rechargeBattery();
        }
        command.execute(robot);
        robotLogger.logRobotState(command, robot);
        robot.updateBatteryLevel();
    }

    /**
     * Recharges battery when needed, then execute the commands in this program.
     * Logging this command to robotLogger.
     * @param program
     */
    public void execute(Program program) {
        for (Command command : program.getCommandList()) {
            if (robot.getBatteryCharge() <= 5) {
                robot.rechargeBattery();
            }
            command.execute(robot);
            robotLogger.logRobotState(command, robot);
            robot.updateBatteryLevel();
        }
    }
}


