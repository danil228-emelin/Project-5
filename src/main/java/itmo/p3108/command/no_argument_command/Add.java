package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.PersonReadingBuilder;
import itmo.p3108.model.Person;

import java.time.ZonedDateTime;

/**
 * Add element in collection
 */
public class Add implements NoArgumentCommand, IndependentCommand {

    private static Add add;
    private final PersonReadingBuilder personReadingBuilder;

    private Add() {
        personReadingBuilder = PersonReadingBuilder.getInstance();
    }

    public static Add getInstance() {
        if (add == null) {
            add = new Add();
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

        return "object created successfully";


    }


    @Override
    public String name() {
        return "add";
    }
}