package itmo.p3108.command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

/**
 * Command reorder,reorder collection in reverse order
 * provided with default comparator,compare by id
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Reorder implements NoArgumentCommand {
    private final static String SUCCESS = "Command reorder:collection reordered ";
    @Setter
    @NonNull
    private Comparator<Person> naturalComparatorOrder = Comparator.comparing(Person::getPersonId);
    private boolean isReversed = false;

    /**
     * if collection has been already reversed,
     * using natural order to return to initial order
     */

    @Override
    public String execute() {
        if (!isReversed) {
            controller.getPersonList().sort(naturalComparatorOrder.reversed());
            isReversed = true;
        } else {
            controller.getPersonList().sort(naturalComparatorOrder);
            isReversed = false;
        }

        return SUCCESS;
    }

    @Override
    public String description() {
        return "reorder:сортировать коллекцию в обратном порядке";
    }

    @Override
    public String name() {
        return "reorder";
    }
}
