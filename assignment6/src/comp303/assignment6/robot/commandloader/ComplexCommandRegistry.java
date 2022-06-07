package comp303.assignment6.robot.commandloader;

import comp303.assignment6.robot.comands.Command;
import comp303.assignment6.robot.comands.ComplexCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ComplexCommandRegistry {

    private Map<String, ComplexCommand> complexCommandMap;
    private CommandFactory commandFactory;

    public ComplexCommandRegistry() {
        complexCommandMap = new HashMap<>();
    }

    public void setCommandFactory(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void registCommand(ComplexCommand command) {
        complexCommandMap.put(command.getName(), command);
    }

    public Map<String, ComplexCommand> getComplexCommandMap() {
        return complexCommandMap;
    }

    public void loadRegistryFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                ComplexCommand complexCommand = new ComplexCommand(parts[0]);
                for (int i = 1; i < parts.length; i++) {
                    Command command = commandFactory.parseCommand(parts[i]);
                    complexCommand.addCommand(command);
                }
                complexCommandMap.put(complexCommand.getName(), complexCommand);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
        }

    }

    public void writeRegistryFile(String filename) {
        try {
            PrintWriter printWriter = new PrintWriter(filename);

            for (ComplexCommand complexCommand : complexCommandMap.values()) {
                printWriter.println(complexCommand.toCommandString());
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
        }
    }

    public ComplexCommand getComplexCommand(String name) {
        return complexCommandMap.get(name);
    }
}
