package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import lombok.NonNull;
import lombok.Setter;

import java.util.Comparator;

/**
 * reorder collection
 * provided with default comparator
 */
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
        return "collection reordered successfully";
    }

    @Override
    public String name() {
        return "reorder";
    }
}
