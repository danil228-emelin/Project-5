package itmo.p3108.command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import itmo.p3108.model.PersonReadingBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * Command Remove Greater,
 * remove elements witch greater than created element
 * after reading command,user creates new element.
 * if new element is greater than elements in collections will be deleted
 * provided with default comparator,compare by name and then birthday
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RemoveGreater implements NoArgumentCommand, IndependentCommand {
    private final static String SUCCESS = "command RemoveGreater deleted all suitable elements ";
    private final static String FAIL = "command RemoveGreater deleted nothing ";
    @Setter
    @NonNull
    private Comparator<Person> comparator = Comparator.comparing(Person::getPersonName).thenComparing(Person::getPersonBirthday);

    /**
     * @return result of command invocation
     * no elements can be deleted
     */
    @Override
    public String execute() {

        PersonReadingBuilder personReadingBuilder = PersonReadingBuilder.getInstance();
        Person person = Person
                .builder()
                .personName(personReadingBuilder.createName())
                .personId(personReadingBuilder.createId())
                .personHeight(personReadingBuilder.createHight())
                .personEyeColor(personReadingBuilder.createColor())
                .personNationality(personReadingBuilder.createNationality())
                .personBirthday(personReadingBuilder.createBirthDay())
                .coordinates(personReadingBuilder.createCoordinates())
                .location(personReadingBuilder.createLocation())
                .build();
        ArrayList<Person> arrayList = controller.getPersonList();
        Collection<Person> collection = arrayList.stream().filter(x -> comparator.compare(x, person) > 0).toList();

        if (arrayList.removeAll(collection)) {
            return SUCCESS;
        }

        return FAIL;
    }

    @Override
    public String description() {
        return "remove_greater {element} : удалить из коллекции все элементы, больше, чем заданный";

    }

    @Override
    public String name() {
        return "remove_greater";
    }

}
