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

import java.util.Comparator;
import java.util.Optional;

/**
 * AddIfMax,add element in collection if it is bigger than the max element in collection
 * If collection is empty add element
 * default comparator compared by name and them by id
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AddIfMax implements NoArgumentCommand, IndependentCommand {
    private final static String SUCCESS = "Command AddIf: new element  added ";
    private final static String FAIL = "Command AddIf: new element didn't added ";
    @Setter
    @NonNull
    private Comparator<Person> comparator = Comparator.comparing(Person::getPersonName).thenComparing(Person::getPersonId);

    /**
     * @return result of execution
     * {@link PersonReadingBuilder} is used to read and check data  from console
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
        if (controller.getPersonList().size() == 0) {
            controller.getPersonList().add(person);

            return SUCCESS;
        }
        Optional<Person> other =
                controller
                        .getPersonList()
                        .stream().parallel().max(comparator);
        if (other.isPresent() && comparator.compare(person, other.get()) > 0) {
            controller.getPersonList().add(person);

            return SUCCESS;
        }

        return FAIL;
    }

    @Override
    public String description() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    @Override
    public String name() {
        return "add_if_max";
    }


}
