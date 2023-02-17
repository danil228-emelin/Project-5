package itmo.p3108.command.one_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.model.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * update exist element
 */
@Slf4j
public class Update implements Command {

    private static Update update;
    private Person person;

    private Update() {
    }

    public static Update getInstance() {
        if (update == null) {
            update = new Update();
            log.info("Update initialized");
        }
        return update;
    }

    public Update findPerson(Long id) {
        if (id <= 0) {
            log.error("Update error:id must be greater than zero ");

            throw new ValidationException("error:id must be greater than zero");
        }
        person = controller.getPersonById(id);
        return this;
    }

    public String createName() {

        System.out.println("change person name or don't chang " + person.getName());
        System.out.println("Press enter-don't change");
        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getName();
            }
            return PersonReadingBuilder.getInstance().createName();
        }
    }


    public Coordinates createCoordinates() {

        System.out.println("change coordinates  or don't change " + person.getCoordinates());
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

        System.out.println("change height or don't change " + person.getHeight());
        System.out.println("press enter-don't change");

        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getHeight();
            }
            return PersonReadingBuilder.getInstance().createHight();
        }
    }

    public LocalDate createBirthDay() {

        System.out.println("change birthday or don't change " + person.getBirthday());
        System.out.println("press enter-don't change");

        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getBirthday();
            }
            return PersonReadingBuilder.getInstance().createBirthDay();
        }
    }

    public Color createColor() {

        System.out.println("change colour or don't change " + person.getEyeColor());
        System.out.println("press enter-don't change");

        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getEyeColor();
            }
            return PersonReadingBuilder.getInstance().createColor();
        }
    }


    public Country createNationality() {

        System.out.println("change nationality or don't change " + person.getNationality());
        System.out.println("press enter-don't change");
        while (true) {
            String s = PersonReadingBuilder.getInstance().properRead("name must be not null");

            if (s.equals("")) {
                return person.getNationality();
            }
            return PersonReadingBuilder.getInstance().createNationality();
        }
    }

    public Location createLocation() {

        System.out.println("change location or don't change " + person.getLocation());
        System.out.println("press enter-don't change");
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
        log.info("Update:element with id "+person.getId() +" is updated");
        return "element is updated";
    }

    @Override
    public String name() {
        return "update";
    }
}
