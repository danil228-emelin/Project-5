package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.util.Invoker;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

/**
 * put out information about commands
 */
@Slf4j
public class Help implements NoArgumentCommand, IndependentCommand {
    private static Help help;

    private Help() {
    }

    public static Help getInstance() {
        if (help == null) {
            help = new Help();
            log.info("Help initialized");
        }
        return help;
    }

    @Override
    public String execute() {
        log.info("Command help print information");

        return Invoker.getInstance().commands()
                .stream()
                .map(Command::description)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String description() {
        return "help : вывести справку по доступным командам";
    }

    @Override
    public String name() {
        return "help";
    }
}
