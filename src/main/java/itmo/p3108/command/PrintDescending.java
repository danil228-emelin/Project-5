package itmo.p3108.command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import lombok.NonNull;
import lombok.Setter;

import java.util.Comparator;
import java.util.stream.Collectors;

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
        }
        return printDescending;
    }

    @Override
    public String execute() {
        Comparator<Person> reversed_comparator = naturalComparatorOrder.reversed();
       return controller
                .getPersonList()
                .stream()
                .sorted(reversed_comparator)
               .map(Person::toString)
               .collect(Collectors.joining(","));
    }

    @Override
    public String name() {
        return "print_descending";
    }
}
