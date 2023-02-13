package itmo.p3108.command;

import itmo.p3108.command.type.Command;
import itmo.p3108.model.Person;
import lombok.NonNull;

import java.util.stream.Collectors;

public class FilterStartsWithName implements Command {

    private static FilterStartsWithName filterStartsWithName;
    private String substring;

    private FilterStartsWithName() {
    }

    public static FilterStartsWithName getInstance() {
        if (filterStartsWithName == null) {
            filterStartsWithName = new FilterStartsWithName();
        }
        return filterStartsWithName;

    }

    @Override
    public String execute() {
        return
                controller
                        .getPersonList()
                        .stream()
                        .filter(x -> x.getName().startsWith(substring))
                        .map(Person::toString)
                        .collect(Collectors.joining(","));
    }

    @Override
    public String name() {
        return "filter_starts_with_name";
    }

    public void setSubstring(@NonNull String substring) {
        this.substring = substring.toLowerCase();
    }
}
