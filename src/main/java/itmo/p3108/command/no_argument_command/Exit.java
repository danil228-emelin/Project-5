package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * exit without saving collection
 */
@Slf4j
public class Exit implements IndependentCommand,NoArgumentCommand {
    private static Exit exit;
    public static Exit getInstance(){
      if(exit==null){
          exit = new Exit();
        log.info("Exit initialized");
      }
    return exit;
    }

    @Override
    public String execute() {
        log.info("Exit from the system");
        log.warn("Collection elements didn't save");

        System.exit(0);

        return "";
    }

    @Override
    public String name() {
        return "exit";
    }
}
