package assignment5;

import java.util.ArrayList;
import java.util.List;

public class CommandProcessor {

    private List<Command> aExecutedCommands = new ArrayList<>();
    private List<Command> aUndoneCommands = new ArrayList<>();

    public void consume(Command pCommand){
        assert pCommand!=null;
        pCommand.execute();
        aExecutedCommands.add(pCommand);
    }
    public void undo(){
        if (!aExecutedCommands.isEmpty()) {
            Command cmd = aExecutedCommands.remove(aExecutedCommands.size() - 1);
            aUndoneCommands.add(cmd);
            cmd.undo();
        }
//        else{
//            System.out.println("no more commands to undo");
//        }
    }
    public void redo(){
        if (!aUndoneCommands.isEmpty()) {
            Command cmd = aUndoneCommands.remove(aUndoneCommands.size() - 1);
            consume(cmd);
        }
        else{
            Command cmd = aUndoneCommands.remove(aExecutedCommands.size()-1);
            cmd.execute(); // repeats the last action
        }
//        else{
//            System.out.println("no more commands to redo");
//        }
    }

}
