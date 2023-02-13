package itmo.p3108.command;

import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.PersonReadingBuilder;
import itmo.p3108.model.Person;

import java.time.ZonedDateTime;

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

        return "Объект успешно создан";


    }


    @Override
    public String name() {
        return "add";
    }
}
