package itmo.p3108.command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import lombok.NonNull;
import lombok.Setter;

import java.util.Comparator;

public class PrintDescending implements NoArgumentCommand {
    private static PrintDescending printDescending;
    @Setter
    @NonNull
    private Comparator<Person> naturalComparatorOrder;

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
        StringBuilder sb = new StringBuilder();
        Comparator<Person> reversed_comparator = naturalComparatorOrder.reversed();
        controller
                .getPersonList()
                .stream()
                .sorted(reversed_comparator)
                .forEach(x -> sb.append(x.toString()).append(" "));
        return sb.toString();
    }

    @Override
    public String name() {
        return "print_descending";
    }
}
