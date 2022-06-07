package comp303.assignment6.robot.commandloader;

import comp303.assignment6.robot.comands.*;

import java.util.HashMap;
import java.util.Map;

public class ConcreteCommandFactory implements CommandFactory {

    ComplexCommandRegistry complexCommandRegistry;

    public ConcreteCommandFactory(ComplexCommandRegistry complexCommandRegistry) {
        this.complexCommandRegistry = complexCommandRegistry;
    }

    /**
     * Parses and executes according to the input command as a string.
     * @param line
     * @return
     */
    @Override
    public Command parseCommand(String line) {
        String[] parts = line.split(" ");
        String cmd = parts[0];
        if (cmd.equals("Move")) {
            return new MoveForwardCommand(Double.parseDouble(parts[1]));
        } else if (cmd.equals("Turn")) {
            if (parts[1].equals("left")) {
                return new TurnCommand(-1);
            } else {
                return new TurnCommand(1);

            }
        } else if (cmd.equals("Grab")) {
            return new GrabCommand();
        } else if (cmd.equals("Release")) {
            return new ReleaseCommand();
        } else if (cmd.equals("Compact")) {
            return new CompactCommand();
        } else if (cmd.equals("Empty")) {
            return new EmptyCommand();
        }

        ComplexCommand complex = complexCommandRegistry.getComplexCommand(cmd);
        return complex;
    }
}
