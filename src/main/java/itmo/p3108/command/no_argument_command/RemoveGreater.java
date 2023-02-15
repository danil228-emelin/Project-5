package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import itmo.p3108.model.PersonReadingBuilder;
import lombok.NonNull;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * remove elements witch greater than created element
 */
public class RemoveGreater implements NoArgumentCommand, IndependentCommand {
    private static RemoveGreater removeGreater;
    @Setter
    @NonNull
    private Comparator<Person> comparator = Comparator.comparing(Person::getName).thenComparing(Person::getBirthday);

    private RemoveGreater() {
    }

    public static RemoveGreater getInstance() {
        if (removeGreater == null) {
            removeGreater = new RemoveGreater();
        }
        return removeGreater;
    }

    @Override
    public String execute() {

        PersonReadingBuilder personReadingBuilder = PersonReadingBuilder.getInstance();
        Person person = Person
                .builder()
                .name(personReadingBuilder.createName())
                .id(personReadingBuilder.createId())
                .height(personReadingBuilder.createHight())
                .eyeColor(personReadingBuilder.createColor())
                .nationality(personReadingBuilder.createNationality())
                .birthday(personReadingBuilder.createBirthDay())
                .coordinates(personReadingBuilder.createCoordinates())
                .creationDate(ZonedDateTime.now())
                .location(personReadingBuilder.createLocation())
                .build();
        ArrayList<Person> arrayList = controller.getPersonList();
        int size = arrayList.size();
        arrayList.removeIf(x -> {
                    if (comparator.compare(x, person) > 0) {
                        System.out.println(x.getName() + " removed ");
                        return true;
                    }
                    return false;
                }
        );
        if (size != arrayList.size()) {
            return "Все подходящие элементы успешно удалены";
        }
        return "Ни один элемент не был удален";
    }

    @Override
    public String name() {
        return "remove_greater";
    }

}
