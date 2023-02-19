package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

/**
 * reorder collection
 * provided with default comparator
 */
@Slf4j
public class Reorder implements NoArgumentCommand {
    private static Reorder reorder;
    @Setter
    @NonNull
    private Comparator<Person> naturalComparatorOrder = Comparator.comparing(Person::getId);
    private boolean isReversed = false;

    private Reorder() {
    }

    public static Reorder getInstance() {
        if (reorder == null) {
            reorder = new Reorder();
            log.info("Reorder initialized");
        }
        return reorder;
    }

    @Override
    public String execute() {
        if (!isReversed) {
            controller.getPersonList().sort(naturalComparatorOrder.reversed());
            isReversed=true;
        } else {
            controller.getPersonList().sort(naturalComparatorOrder);
            isReversed=false;
        }
        log.info("Command reorder:collection reordered ");

        return "collection reordered ";
    }

    @Override
    public String description() {
        return  "reorder:сортировать коллекцию в обратном порядке";
    }

    @Override
    public String name() {
        return "reorder";
    }
}
