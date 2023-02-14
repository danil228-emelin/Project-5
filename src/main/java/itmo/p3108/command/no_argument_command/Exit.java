package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;

public class Exit implements IndependentCommand,NoArgumentCommand {
    private static Exit exit;
    public static Exit getInstance(){
      if(exit==null){
          exit = new Exit();}
    return exit;
    }

    @Override
    public String execute() {
        System.exit(0);
        return "";
    }

    @Override
    public String name() {
        return "exit";
    }
}
