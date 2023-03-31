package itmo.p3108.command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.Person;
import itmo.p3108.model.PersonReadingBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

/**
 * Command Add,add element in collection
 * <p>
 * User enters data,but  while script executing Add take arguments from script file
 * Next line is treated as arguments
 *
 * @see ExecuteScript
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SuppressWarnings("unused")
public class Add implements NoArgumentCommand, IndependentCommand {
    private static final PersonReadingBuilder personReadingBuilder = PersonReadingBuilder.getInstance();

    /**
     * @return result of execution
     * {@link PersonReadingBuilder} is used to read and check information from  console
     */
    @Override
    public String execute() {
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
        person.setPersonCreationDate(ZonedDateTime.now());
        controller.getPersonList().add(person);

        return "object created ";


    }

    @Override
    public String description() {
        return "add {element} : добавить новый элемент в коллекцию";
    }


    @Override
    public String name() {
        return "add";
    }
}
