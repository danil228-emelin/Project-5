package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

/**
 * print the elements of collection in order
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Show implements NoArgumentCommand {

    @Override
    public String execute() {
        log.info("command Show:print elements ");

        return controller.getPersonList().stream()
                .map(Person::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String description() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String name() {
        return "show";
    }
}
