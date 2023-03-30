package itmo.p3108.command;

import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.util.Invoker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

/**
 * Command Help,put out information about commands
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SuppressWarnings("unused")
public class Help implements NoArgumentCommand, IndependentCommand {
    /**
     * @return main  information about commands
     */
    @Override
    public String execute() {

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
