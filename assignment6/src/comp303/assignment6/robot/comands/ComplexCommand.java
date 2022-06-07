package comp303.assignment6.robot.comands;

import comp303.assignment6.robot.Robot;

import java.util.ArrayList;
import java.util.List;

public class ComplexCommand extends Command {
    private List<Command> commandList;

    public ComplexCommand(String name) {
        super(name);
        commandList = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commandList.add(command);
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    @Override
    public void execute(Robot robot) {
        for (Command command : commandList) {
            command.execute(robot);
        }
    }

    /**
     * The instructions of how to perform the complex action into String.
     * @return
     */
    @Override
    public String toCommandString() {
        List<String> slst = new ArrayList<>();
        slst.add(name);
        for (Command command : commandList) {
            slst.add(command.toCommandString());
        }
        return String.join(",", slst);
    }
}
