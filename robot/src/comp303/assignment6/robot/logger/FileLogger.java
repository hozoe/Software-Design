package comp303.assignment6.robot.logger;

import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.comands.Command;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This class is only useful for executing actions from a file instead of typing it in the console command by command.
 * Mostly for testing purposes.
 */
public class FileLogger implements RobotLogger{

    PrintWriter printWriter;

    public FileLogger(String filename) {
        try {
            printWriter = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void logRobotState(Command command, Robot robot) {
        if(printWriter!=null) {
            printWriter.printf("%s action performed, battery level is %d\n", command.toCommandString(), robot.getBatteryCharge());
        }
    }
}
