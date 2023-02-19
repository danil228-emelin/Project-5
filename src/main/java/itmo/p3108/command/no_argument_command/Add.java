package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.PersonReadingBuilder;
import itmo.p3108.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

/**
 * Add element in collection
 */
@Slf4j
public class Add implements NoArgumentCommand, IndependentCommand {

    private static Add add;
    private final PersonReadingBuilder personReadingBuilder;

    private Add() {
        personReadingBuilder = PersonReadingBuilder.getInstance();
    }

    public static Add getInstance() {
        if (add == null) {
            add = new Add();
            log.info("Command Add initialized");
        }
        return add;
    }


    /**
     *
     * @return result of execution
     * {@link PersonReadingBuilder} is used to read from console
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
        controller.add(person);
        log.info("Command Add:  object created  ");

        return "object created ";


    }


    @Override
    public String name() {
        return "add";
    }
}
