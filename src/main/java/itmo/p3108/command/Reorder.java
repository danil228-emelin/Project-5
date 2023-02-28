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
    @Setter
    @NonNull
    private Comparator<Person> naturalComparatorOrder = Comparator.comparing(Person::getId);
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
        log.info("Command reorder:collection reordered ");

        return "collection reordered ";
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
