package itmo.p3108.command.one_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.model.*;

import java.time.LocalDate;

public class Update implements Command {

    private static Update update;
    private Person person;

    private Update() {
    }

    public static Update getInstance() {
        if (update == null) {
            update = new Update();
        }
        return update;
    }

    public Update findPerson(Long id) {
        if (id <= 0) {
            throw new ValidationException("Ошибка:id-натуральное число");
        }
        person = controller.getPersonById(id);
        return this;
    }

    public String createName() {

        System.out.println("Изменить имя пользователя или оставить " + person.getName());
        System.out.println("Введите enter,чтобы ничего не менять");
        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getName();
            }
            return PersonReadingBuilder.getInstance().createName();
        }
    }


    public Coordinates createCoordinates() {

        System.out.println("Изменить координаты  пользователя или оставить " + person.getCoordinates());
        System.out.println("Введите enter,чтобы ничего не менять");
        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getCoordinates();
            }
            return PersonReadingBuilder.getInstance().createCoordinates();
        }
    }

    public double createHeight() {

        System.out.println("Изменить рост  пользователя или оставить " + person.getHeight());
        System.out.println("Введите enter,чтобы ничего не менять");

        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getHeight();
            }
            return PersonReadingBuilder.getInstance().createHight();
        }
    }

    public LocalDate createBirthDay() {

        System.out.println("Изменить день рождение  пользователя или оставить " + person.getBirthday());
        System.out.println("Введите enter,чтобы ничего не менять");

        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getBirthday();
            }
            return PersonReadingBuilder.getInstance().createBirthDay();
        }
    }

    public Color createColor() {

        System.out.println("Изменить  цвет глаз  пользователя или оставить " + person.getEyeColor());
        System.out.println("Введите enter,чтобы ничего не менять");

        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getEyeColor();
            }
            return PersonReadingBuilder.getInstance().createColor();
        }
    }


    public Country createNationality() {

        System.out.println("Изменить  гражданство  пользователя или оставить " + person.getNationality());
        System.out.println("Введите enter,чтобы ничего не менять");
        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getNationality();
            }
            return PersonReadingBuilder.getInstance().createNationality();
        }
    }

    public Location createLocation() {

        System.out.println("Изменить  локацию  пользователя или оставить " + person.getLocation());
        System.out.println("Введите enter,чтобы ничего не менять");
        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getLocation();
            }
            return PersonReadingBuilder.getInstance().createLocation();
        }
    }


    @Override
    public String execute() {

        person.setName(createName());
        person.setCoordinates(createCoordinates());
        person.setHeight(createHeight());
        person.setBirthday(createBirthDay());
        person.setEyeColor(createColor());
        person.setNationality(createNationality());
        person.setLocation(createLocation());

        return "Элемент обновлен";
    }

    @Override
    public String name() {
        return "update";
    }
}
