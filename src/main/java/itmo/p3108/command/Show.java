package itmo.p3108.command;

import itmo.p3108.model.Person;

import java.util.stream.Collectors;

public class Show implements NoArgumentCommand {
    @Override
    public String execute() {
        return controller.getPersonList().stream()
                .map(Person::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public String name() {
        return "show";
    }
}
