package itmo.p3108.util;

import com.sun.istack.Nullable;
import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.model.*;
import itmo.p3108.util.annotation.Checking;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Class CheckData,check each attribute of @see {@link itmo.p3108.model.Person}
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CheckData {
    @SuppressWarnings("all")
    private final String CREATION_TIME_FORMAT = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z";
    @SuppressWarnings("all")
    private final String BIRTHDAY_FORMAT = "[0-9][1-9]-[0-9][1-9]-[1-9]\\d{3}";
    @SuppressWarnings("all")
    private final String INT_NUMBER_FORMAT = "-?\\d+";
    @SuppressWarnings("all")
    private final String POSITIVE_NUMBER_FORMAT = "\\d+";
    @SuppressWarnings("all")
    private final String FLOAT_NUMBER_FORMAT = "-?\\d+\\.?\\d*";
    @SuppressWarnings("all")
    private final String POSITIVE_FLOAT_NUMBER_FORMAT = "\\d+\\.?\\d*";
    @SuppressWarnings("all")
    private final String NAME_FORMAT = "(\\w+-?\\w*)";

    /**
     * @see Country
     */
    @Checking
    public boolean checkPersonId(Long id) {
        return id != null && id > 0;
    }

    @Checking
    public boolean checkCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            return false;
        }
        return checkCoordinatesX(coordinates.getCoordinatesX().toString()) &&
                checkCoordinatesY(coordinates.getCoordinatesY().toString());
    }

    @Checking
    public boolean checkPersonCreationDate(ZonedDateTime creationDate) {
        return creationDate != null;
    }

    @Checking
    public boolean checkPersonHeight(Double height) {
        if (height == null) {
            return false;
        }
        return checkPersonHeight(height.toString());
    }

    @Checking
    public boolean checkPersonBirthday(LocalDate localDate) {
        return localDate != null;
    }

    @Checking
    public boolean checkPersonEyeColor(Color color) {
        return color != null;
    }

    @Checking
    public boolean checkPersonNationality(Country country) {
        return country != null;
    }

    @Checking
    public boolean checkLocation(Location location) {
        if (location == null) {
            return false;
        }
        return checkLocationName(location.getLocationName()) && checkLocationZ(location.getLocationY().toString())
                && checkLocationY(location.getLocationY().toString()) && checkLocationX(location.getLocationX().toString());
    }

    public boolean checkPersonNationalityReadingFromFile(String test) {

        if (!Country.isPresent(test)) {
            log.error("error:during nationality setting line has wrong format");
            System.err.println("error:during nationality setting line has wrong format");
            return false;
        }
        return true;
    }

    public boolean checkPersonColourReadingConsole(String test) {
        if (!test.matches("[1-5]")) {
            log.error("error:during colour setting line has wrong format");
            System.err.println("error:during colour setting line has wrong format");
            return false;

        }
        return true;
    }

    /**
     * @see Color
     */
    public boolean checkPersonEyeColorReadingFile(String test) {
        if (!Color.isPresent(test)) {
            log.error("error:during colour setting line has wrong format");
            System.err.println("error:during colour setting line has wrong format");
            return false;

        }
        return true;
    }


    public boolean checkPersonBirthday(String test) {
        if (!test.matches(BIRTHDAY_FORMAT)) {
            log.error("error:during birthday setting line has wrong format");
            System.err.println("error:during birthday setting line has wrong format");
            return false;
        }
        String[] strings = test.split("-");

        if (Integer.parseInt(strings[0]) > 12) {
            log.error("error:during birthday setting value is incorrect");

            System.err.println("error:during birthday setting value is incorrect\n month can't be greater than 12");
            return false;

        }

        if (Integer.parseInt(strings[1]) > 31) {
            log.error("error:during birthday setting value is incorrect");
            System.err.println("error:during birthday setting value is incorrect\n day can't be greater than 31");
            return false;

        }


        if (Integer.parseInt(strings[2]) > 2024 || Integer.parseInt(strings[2]) < 1920) {
            log.error("error:during birthday setting value is incorrect");
            System.err.println("error:during birthday setting value is incorrect \n year can't be greater than 2023 and less than 1920");
            return false;

        }

        return true;
    }

    public boolean checkPersonNationalityReadingFromConsole(String test) {
        if (!test.matches("[1-4]")) {
            log.error("error:during nationality setting line has wrong format");
            System.err.println("error:during nationality setting line has wrong format");
            return false;
        }
        return true;
    }

    public boolean checkCoordinatesX(String test) {
        if (!test.matches(INT_NUMBER_FORMAT)) {
            log.error("error:during coordinate x setting, wrong format");
            System.err.println("error:during coordinate x setting, wrong format \n x is integer");
            return false;

        }
        if (test.length() > 15) {
            log.error("error:during coordinate x setting,value is too large");
            System.err.println("error:during coordinate x setting,value is too large");
            return false;

        }
        return true;
    }

    public boolean checkCoordinatesY(String test) {
        if (!test.matches(FLOAT_NUMBER_FORMAT)) {
            log.error("error:during coordinate y setting");
            System.err.println("error:during coordinate y setting \n value is whole or fractional number");
            return false;

        }
        if (test.length() > 15) {
            log.error("error:during coordinate y setting value is too large");
            System.err.println("error:during coordinate y setting \n value is too large");
            return false;

        }
        return true;
    }

    public boolean checkLocationX(String test) {
        if (!test.matches(FLOAT_NUMBER_FORMAT)) {
            log.error("error:during location coordinate x setting,wrong format");
            System.err.println("error:during location coordinate x setting \n value is whole or fractional number");
            return false;

        }
        if (test.length() > 15) {
            log.error("error:during location coordinate x setting value is too large");
            System.err.println("error:during location coordinate x setting \n value is too large");
            return false;

        }
        return true;
    }

    public boolean checkLocationY(String test) {

        if (!test.matches(FLOAT_NUMBER_FORMAT)) {
            log.error("error:during location coordinate y setting wrong format");

            System.err.println("error:during location coordinate y setting \n value is whole or fractional number");
            return false;

        }
        if (test.length() > 15) {
            log.error("error:during location coordinate y setting value is too large");

            System.err.println("error:during location coordinate y setting \n value is too large");
            return false;

        }
        return true;
    }

    public boolean checkPersonId(String test) {
        if (!test.matches(POSITIVE_NUMBER_FORMAT)) {
            log.error("error:id has wrong format");

            System.err.println("error:id has wrong format \n id is natural number");
            return false;

        }
        Long id = Long.parseLong(test);
        if (Command.controller.isPersonExist(id)) {
            log.error("error:id has wrong format");
            System.err.printf("error:id has wrong format  person with %s  already exist\n", test);
            return false;
        }
        return true;
    }

    public boolean checkLocationZ(String test) {

        if (!test.matches(FLOAT_NUMBER_FORMAT)) {
            log.error("error: error:during location coordinate z setting");
            System.err.println("error:during location coordinate z setting \n value is whole or fractional number");
            return false;

        }
        if (test.length() > 15) {
            log.error("error: error:during location coordinate z setting,value is too large");
            System.err.println("error:during location coordinate z setting \n value is too large");
            return false;

        }
        return true;
    }

    public boolean checkPersonHeight(String test) {
        if (!test.matches(POSITIVE_FLOAT_NUMBER_FORMAT)) {
            log.error("error:during height setting");
            System.err.println("error:during height setting \n value is positive whole or fractional number");
            return false;

        }
        if (test.length() > 15) {
            log.error("error:during height setting");
            System.err.println("error:during height setting value is too large");
            return false;

        }
        return true;
    }

    public boolean checkPersonCreationDate(String test) {

        if (!test.matches(CREATION_TIME_FORMAT)) {
            System.err.println("error:creation time has wrong format");
            return false;

        }
        return true;
    }

    public boolean checkLocationName(String test) {
        return checkPersonName(test);
    }

    @Checking
    public boolean checkPersonName(String test) {

        if (test.length() > 40) {
            log.error("error during name setting:too long line");
            System.err.println("error during name setting:too long line \n maximum is 40 letters");
            return false;

        }
        if (!test.matches(NAME_FORMAT)) {
            log.error("error during name setting:line has wrong format ");

            System.err.println("error during name setting:line has wrong format \n use only digits,letters,and dash for double name");
            return false;

        }
        return true;
    }

    public boolean checkArgument(@NonNull String argument, Method checkMethod) {
        PrintStream error = System.err;
        System.setErr(new PrintStream(OutputStream.nullOutputStream()));
        boolean result = wrapperCheckArgument(argument, checkMethod);
        System.setErr(error);
        return result;
    }

    @Nullable
    private boolean wrapperCheckArgument(@NonNull String argument, Method checkMethod) {
        try {

            return (boolean) checkMethod.invoke(this, argument);
        } catch (InvocationTargetException | IllegalAccessException exception) {
            System.err.println("CheckMethod is incorrect");
        }
        return false;
    }

    /**
     * helper method for @see {@link CheckData#checkArguments(Collection)}
     * Methods, witch validate data,print errors in system.err.
     * in order to suppress  message while checkArguments is executing
     * helper method redirect error flow in nullOutputStream
     * after execution it redirects error flow back
     */

    public boolean wrapperCheckArguments(@NonNull Collection<String> collection) {
        PrintStream error = System.err;
        System.setErr(new PrintStream(OutputStream.nullOutputStream()));
        boolean result = checkArguments(collection);
        System.setErr(error);
        return result;
    }


    /**
     * it is implied that  @param collection has attributes of @see {@link itmo.p3108.model.Person}
     * Method checked whether all attributes have wright format
     */
    private boolean checkArguments(@NonNull Collection<String> collection) {

        Optional<Set<Method>> set = Reflection.findAllMethodsWithAnnotation("itmo.p3108.util", Checking.class);
        if (set.isEmpty()) {
            return false;
        }
        CheckData checkData = new CheckData();
        boolean oneArgumentChecking = false;
        for (String data : collection) {
            for (Method methodChecking : set.get()) {
                try {

                    Object result = methodChecking.invoke(checkData, data);
                    if (result instanceof Boolean resultChecking && resultChecking) {
                        oneArgumentChecking = true;
                        break;
                    }
                    if (oneArgumentChecking) {
                        oneArgumentChecking = false;
                    } else {
                        throw new ValidationException("Error during checking:one attribute has wrong format");
                    }
                } catch (Exception exception) {
                    log.error(exception.getMessage());
                }

            }
        }
        return true;
    }

    public void checkPersonCollection(List<Person> personList) {
        List<Person> removePerson = new ArrayList<>(personList.size());
        Optional<Set<Method>> checkMethods = Reflection.findAllMethodsWithAnnotation("itmo.p3108.util", Checking.class);
        Field[] personFields = Reflection.findAllFields(Person.class);
        if (checkMethods.isEmpty()) {
            System.err.println("Error during checkPersonCollection:checkMethods are missed");
            return;
        }
        Iterator<Person> iterator = personList.listIterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            for (Field personField : personFields) {
                personField.setAccessible(true);
                boolean validationResult = false;
                for (Method checkMethod : checkMethods.get()) {
                    if (checkMethod.getName().toLowerCase().contains(personField.getName().toLowerCase())) {
                        try {
                            validationResult = (boolean) checkMethod.invoke(this, personField.get(person));
                            if (!validationResult) {
                                removePerson.add(person);
                            }
                            break;

                        } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                            log.error(e.getMessage());
                            System.out.println(e.getMessage());
                        }
                    }
                }
                if (!validationResult) {
                    break;
                }
            }
        }
        for (Person removedPerson : removePerson) {
            personList.remove(removedPerson);
        }
    }

}
