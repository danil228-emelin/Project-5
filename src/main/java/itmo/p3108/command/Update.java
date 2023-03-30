package itmo.p3108.command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * Command update,update  exist element
 * has id of element  as parameter.
 * User has opportunity to stay previous state of attribute
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Update implements Command {
    private Person person;

    public Update findPerson(Long id) {
        if (id <= 0) {
            log.error("Update error:id must be greater than zero ");

            throw new ValidationException("error:id must be greater than zero");
        }
        person = controller.getPersonById(id);
        return this;
    }

    public String createName() {

        System.out.println("person name " + person.getPersonName());
        System.out.println("Press enter-don't change");
        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getPersonName();
            }
            return PersonReadingBuilder.getInstance().createName();
        }
    }


    public Coordinates createCoordinates() {

        System.out.println(" change coordinates  " + person.getCoordinates());
        System.out.println("press enter-don't change");
        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getCoordinates();
            }
            return PersonReadingBuilder.getInstance().createCoordinates();
        }
    }

    public double createHeight() {

        System.out.println(" height  " + person.getPersonHeight());
        System.out.println("press enter-don't change");

        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getPersonHeight();
            }
            return PersonReadingBuilder.getInstance().createHight();
        }
    }

    public LocalDate createBirthDay() {

        System.out.println(" birthday  " + person.getPersonBirthday());
        System.out.println("press enter-don't change");

        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getPersonBirthday();
            }
            return PersonReadingBuilder.getInstance().createBirthDay();
        }
    }

    public Color createColor() {

        System.out.println(" colour  " + person.getPersonEyeColor());
        System.out.println("press enter-don't change");

        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getPersonEyeColor();
            }
            return PersonReadingBuilder.getInstance().createColor();
        }
    }


    public Country createNationality() {

        System.out.println(" nationality  " + person.getPersonNationality());
        System.out.println("press enter-don't change");
        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getPersonNationality();
            }
            return PersonReadingBuilder.getInstance().createNationality();
        }
    }

    public Location createLocation() {

        System.out.println(" location  " + person.getLocation());
        System.out.println("press enter-don't change");
        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {

                return person.getLocation();
            }
            return PersonReadingBuilder.getInstance().createLocation();
        }
    }


    /**
     * using private methods for rewriting each  element attribute
     */
    @Override
    public String execute() {

        person.setPersonName(createName());
        person.setCoordinates(createCoordinates());
        person.setPersonHeight(createHeight());
        person.setPersonBirthday(createBirthDay());
        person.setPersonEyeColor(createColor());
        person.setPersonNationality(createNationality());
        person.setLocation(createLocation());
        return "element is updated";
    }

    @Override
    public String description() {
        return "update id:обновить элемент с заданным id";
    }

    @Override
    public String name() {
        return "update";
    }
}
