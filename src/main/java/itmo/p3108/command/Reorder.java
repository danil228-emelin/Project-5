package itmo.p3108.command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import lombok.NonNull;
import lombok.Setter;

import java.util.Comparator;

public class Reorder implements NoArgumentCommand {
    private static Reorder reorder;
    @Setter
    @NonNull
    private Comparator<Person> naturalComparatorOrder=Comparator.comparing(Person::getBirthday).thenComparing(Person::getId);

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
        controller.getPersonList().sort(naturalComparatorOrder.reversed());
        return "Коллекция отсортирована в обратном порядке";
    }

    @Override
    public String name() {
        return "reorder";
    }
}
