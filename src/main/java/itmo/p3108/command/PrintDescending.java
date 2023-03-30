package itmo.p3108.command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Command Print Descending, print in descending order
 * provided with default comparator,compare by id
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PrintDescending implements NoArgumentCommand {
    @Setter
    @NonNull
    private Comparator<Person> naturalComparatorOrder = (Comparator.comparing(Person::getPersonId));

    @Override
    public String execute() {
        Comparator<Person> reversed_comparator = naturalComparatorOrder.reversed();
        return controller
                .getPersonList()
                .stream()
                .sorted(reversed_comparator)
                .parallel()
                .map(Person::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String description() {
        return "print_descending : вывести элементы коллекции в порядке убывания";
    }

    @Override
    public String name() {
        return "print_descending";
    }
}
