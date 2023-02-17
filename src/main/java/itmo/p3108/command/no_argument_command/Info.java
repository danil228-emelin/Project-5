package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import lombok.extern.slf4j.Slf4j;

/**
 * put out major information of collection
 */
@Slf4j
public class Info implements NoArgumentCommand, IndependentCommand {
    private static Info info;

    private Info() {
    }

    public static Info getInstance() {
        if (info == null) {
            info = new Info();
            log.info("Info initialized");
        }
        return info;
    }

    @Override

    public String execute() {

        log.info("Command info print information");

        return controller.info();
    }

    @Override
    public String name() {
        return "info";
    }
}
