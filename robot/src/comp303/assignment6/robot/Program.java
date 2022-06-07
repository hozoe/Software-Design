package comp303.assignment6.robot;

import comp303.assignment6.robot.comands.Command;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private List<Command> commandList;

    public Program() {
        commandList = new ArrayList<>();
    }

    public Program(String scriptFileName) {
        commandList = new ArrayList<>();
        readScript(scriptFileName);
    }

    /**
     * Adds a command to the commandList.
     * @param command
     */
    public void addCommand(Command command) {
        commandList.add(command);
    }

    public void readScript(String scriptFile) {
        commandList.clear();
    }

    /**
     * List of commands in the commandList
     * @return a list of strings of commands
     */
    public List<String> toScript() {
        List<String> result = new ArrayList<>();
        for (Command command : commandList) {
            result.add(command.toCommandString());
        }
        return result;
    }

    /**
     * @return the commandList
     */
    public List<Command> getCommandList() {
        return commandList;
    }
}
