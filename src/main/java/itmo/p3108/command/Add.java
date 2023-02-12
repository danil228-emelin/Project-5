package itmo.p3108.command;

import itmo.p3108.command.type.CreationCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.command.util.PersonReadingBuilder;
import itmo.p3108.model.Person;

import java.time.ZonedDateTime;

public class Add implements CreationCommand, NoArgumentCommand {

    private static Add add;
    private final PersonReadingBuilder receiver;

    private Add() {
        receiver = PersonReadingBuilder.getInstance();
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
                .name(receiver.createName())
                .id(receiver.createId())
                .height(receiver.createHight())
                .eyeColor(receiver.createColor())
                .nationality(receiver.createNationality())
                .birthday(receiver.createBirthDay())
                .coordinates(receiver.createCoordinates())
                .creationDate(ZonedDateTime.now())
                .location(receiver.createLocation())
                .build();
        controller.add(person);

        return "Объект успешно создан";


    }


    @Override
    public String name() {
        return "add";
    }
}
