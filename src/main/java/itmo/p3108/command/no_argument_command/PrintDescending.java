package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * print in descending order
 * provided with default comparator
 */
@Slf4j
public class PrintDescending implements NoArgumentCommand {
    private static PrintDescending printDescending;
    @Setter
    @NonNull
    private Comparator<Person> naturalComparatorOrder=(Comparator.comparing(Person::getName).thenComparing(Person::getId));

    private PrintDescending() {
    }

    public static PrintDescending getInstance() {
        if (printDescending == null) {
            printDescending = new PrintDescending();
        log.info("PrintDescending initialized");
        }
        return printDescending;
    }

    @Override
    public String execute() {
        Comparator<Person> reversed_comparator = naturalComparatorOrder.reversed();
        log.info("Command printDescending print collection");
        return controller
                .getPersonList()
                .stream()
                .sorted(reversed_comparator)
               .parallel()
               .map(Person::toString)
               .collect(Collectors.joining("\n"));
    }

    @Override
    public String name() {
        return "print_descending";
    }
}
