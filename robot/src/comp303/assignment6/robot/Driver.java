package comp303.assignment6.robot;

import comp303.assignment6.robot.comands.Command;
import comp303.assignment6.robot.comands.ComplexCommand;
import comp303.assignment6.robot.commandloader.CommandFactory;
import comp303.assignment6.robot.commandloader.ComplexCommandRegistry;
import comp303.assignment6.robot.commandloader.ConcreteCommandFactory;
import comp303.assignment6.robot.logger.ConsoleLogger;
import comp303.assignment6.robot.logger.RobotLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver {

    static Scanner scanner;
    static Robot robot;
    static RobotLogger logger;
    static CommandFactory commandFactory;
    static RobotController robotController;
    static ComplexCommandRegistry complexCommandRegistry;

    static final String complexRegistryFile = "complex.txt";

    public static void main(String[] args) {
        robot = new WallE();
        logger = new ConsoleLogger();
        scanner = new Scanner(System.in);
        complexCommandRegistry = new ComplexCommandRegistry();
        commandFactory = new ConcreteCommandFactory(complexCommandRegistry);
        complexCommandRegistry.setCommandFactory(commandFactory);
        robotController = new RobotController(robot, logger);

        complexCommandRegistry.loadRegistryFile(complexRegistryFile);

        while (true) {
            System.out.println("1. Execute Command");
            System.out.println("2. Write Program");
            System.out.println("3. Execute Program");
            System.out.println("4. Add customer command");
            System.out.println("5. Exit");
            System.out.print("Enter your option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (choice == 1) {
                executeCommandMenu();
            } else if (choice == 2) {
                writeProgramMenu();
            } else if (choice == 3) {
                executeProgramMenu();
            } else if (choice == 4) {
                addCustomCommandMenu();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice!");
            }
            System.out.println();
        }

    }

    /**
     * For clients to implement a complex action.
     * After entering your complex command name, input what should be done in order to execute your command
     * The file input1.txt demonstrates how to utilize this function.
     * The action "Back" simply means moving backwards.
     */
    private static void addCustomCommandMenu() {
        System.out.print("Enter your custom command name: ");
        String name = scanner.next();
        scanner.nextLine();

        ComplexCommand complexCommand = new ComplexCommand(name);
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("end")) {
                break;
            }
            Command command = commandFactory.parseCommand(line);
            if (command != null) {
                complexCommand.addCommand(command);
            } else {
                System.out.println("Unknown command!");
            }
        }
        complexCommandRegistry.registCommand(complexCommand);
        complexCommandRegistry.writeRegistryFile(complexRegistryFile);
    }

    /**
     * Execute this single command.
     */
    static void executeCommandMenu() {
        System.out.print("> ");
        String line = scanner.nextLine();
        Command command = commandFactory.parseCommand(line);
        if (command != null) {
            robotController.execute(command);
        } else {
            System.out.println("Unknown command!");
        }
    }

    /**
     * Sequence of actions (commands) that the robot will perform written into a file.
     */
    private static void writeProgramMenu() {
        Program program = new Program();
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("end")) {
                break;
            }
            Command command = commandFactory.parseCommand(line);
            if (command != null) {
                program.addCommand(command);
                System.out.println("Command added");
            } else {
                System.out.println("Unknown command!");
            }
        }
        System.out.println("Enter a filename: ");
        String filename = scanner.nextLine();
        try {
            PrintWriter printWriter = new PrintWriter(filename);
            for (String line : program.toScript()) {
                printWriter.println(line);
            }
            printWriter.flush();
            printWriter.close();
            System.out.println("Program saved to: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file!");
        }
    }

    /**
     * Executing the commands in the program of the input file.
     */
    private static void executeProgramMenu() {
        System.out.println("Enter a filename: ");
        String filename = scanner.nextLine();
        Program program = new Program();
        try {
            Scanner fscanner = new Scanner(new File(filename));
            while (fscanner.hasNextLine()) {
                String line = fscanner.nextLine();
                Command cmd = commandFactory.parseCommand(line);
                program.addCommand(cmd);
            }
            System.out.println("Program read success!");
            System.out.println();
            robotController.execute(program);
        } catch (FileNotFoundException e) {
        }
    }

}
