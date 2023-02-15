package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import itmo.p3108.model.PersonReadingBuilder;
import lombok.NonNull;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Optional;

/**
 * Add element in collection if it is bigger than the max element in collection
 * If collection is empty add element
 */
public class AddIfMax implements NoArgumentCommand, IndependentCommand {
    private static AddIfMax addIfMax;
    @Setter
    @NonNull
    private Comparator<Person> comparator = Comparator.comparing(Person::getName).thenComparing(Person::getId);

    private AddIfMax() {
    }

    public static AddIfMax getInstance() {
        if (addIfMax == null) {
            addIfMax = new AddIfMax();
        }
        return addIfMax;
    }
    /**
     *
     * @return result of execution
     * {@link PersonReadingBuilder} is used to read from console
     */
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
        if (controller.getPersonList().size() == 0) {
            controller.add(person);
            return "element added successfully";
        }
        Optional<Person> other =
                controller
                        .getPersonList()
                        .stream().max(comparator);
        if (other.isPresent() && comparator.compare(person, other.get()) > 0) {
            controller.add(person);
            return "element added successfully";
        }
        return "element wasn't  added ";
    }

    @Override
    public String name() {
        return "add_if_max";
    }


}
