package itmo.p3108.util;

import itmo.p3108.command.*;
import itmo.p3108.model.Person;

import java.util.Comparator;

public class InvokerFactory {
    private InvokerFactory() {
    }

    public static Invoker createInvoker() {
        Invoker invoker = Invoker.getInstance();
        PrintDescending printDescending = PrintDescending.getInstance();
        Reorder reorder = Reorder.getInstance();
        reorder.setNaturalComparatorOrder(Comparator.comparing(Person::getBirthday).thenComparing(Person::getId));
        printDescending.setNaturalComparatorOrder(Comparator.comparing(Person::getName).thenComparing(Person::getId));
        invoker.add(
                Help.getInstance(),
                Clear.getInstance(),
                Info.getInstance(),
                Show.getInstance(),
                printDescending,
                reorder,
                Add.getInstance(),
                Update.getInstance(),
                RemoveById.getInstance(),
                Exit.getInstance(),
                CountByHeight.getInstance());
        return invoker;

    }
}
