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
 * User enters data,but  while script executing Add take arguments from script file
 * Next line is treated as arguments
 *
 * @see ExecuteScript
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SuppressWarnings("unused")
public class Add implements NoArgumentCommand, IndependentCommand {
    private final PersonReadingBuilder personReadingBuilder = PersonReadingBuilder.getInstance();

    /**
     * @return result of execution
     * {@link PersonReadingBuilder} is used to read and check information from  console
     */
    @Override
    public String execute() {
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
        controller.getPersonList().add(person);
        log.info("Command Add:  object created  ");

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
