package comp303.assignment6.robot.commandloader;

import comp303.assignment6.robot.comands.Command;

public interface CommandFactory {
    Command parseCommand(String line);
}
