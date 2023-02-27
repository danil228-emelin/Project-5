package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import itmo.p3108.model.Color;
import itmo.p3108.model.Country;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * set path  file for serialization
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CheckData {
    private final String CREATION_TIME_FORMAT = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z";
    private final String BIRTHDAY_FORMAT = "[0-9][1-9]-[0-9][1-9]-[1-9]\\d{3}";
    private final String INT_NUMBER_FORMAT = "-?\\d+";
    private final String POSITIVE_NUMBER_FORMAT = "\\d+";
    private final String FLOAT_NUMBER_FORMAT = "-?\\d+\\.?\\d*";
    private final String POSITIVE_FLOAT_NUMBER_FORMAT = "\\d+\\.?\\d*";
    private final String NAME_FORMAT = "(\\w+-?\\w*)";

    public boolean checkNationalityReadingFromFile(String test) {

        if (!Country.isPresent(test)) {
            System.err.println("error:during nationality setting line has wrong format");
            log.error("error:during nationality setting line has wrong format");
            return false;
        }
        return true;
    }

    public boolean checkColourReadingConsole(String test) {
        if (!test.matches("[1-5]")) {
            System.err.println("error:during colour setting line has wrong format");
            log.error("error:during colour setting line has wrong format");
            return false;
        }
        return true;
    }

    public boolean checkColourReadingFile(String test) {
        if (!Color.isPresent(test)) {
            System.err.println("error:during colour setting line has wrong format");
            log.error("error:during colour setting line has wrong format");
            return false;
        }
        return true;
    }

    public boolean checkBirthday(String test) {
        if (!test.matches(BIRTHDAY_FORMAT)) {
            System.err.println("error:during birthday setting line has wrong format");
            log.error("error:during birthday setting line has wrong format");
            return false;
        }
        String[] strings = test.split("-");

        if (Integer.parseInt(strings[0]) > 12) {
            System.err.println("error:during birthday setting value is incorrect");
            System.err.println("month can't be greater than 12");
            log.error("error:during birthday setting value is incorrect");
            return false;
        }

        if (Integer.parseInt(strings[1]) > 31) {
            System.err.println("error:during birthday setting value is incorrect");
            System.err.println("day can't be greater than 31");
            log.error("error:during birthday setting value is incorrect");
            return false;
        }


        if (Integer.parseInt(strings[2]) > 2024 || Integer.parseInt(strings[2]) < 1920) {
            System.err.println("error:during birthday setting value is incorrect");
            System.err.println("year can't be greater than 2023 and less than 1920");
            log.error("error:during birthday setting value is incorrect");
            return false;
        }

        return true;
    }

    public boolean checkNationalityReadingFromConsole(String test) {
        if (!test.matches("[1-4]")) {
            System.err.println("error:during nationality setting line has wrong format");
            log.error("error:during nationality setting line has wrong format");
            return false;
        }
        return true;
    }

    public boolean checkCoordinateX(String test) {
        if (!test.matches(INT_NUMBER_FORMAT)) {
            System.err.println("error:during coordinate x setting, wrong format");
            System.err.println("x is integer");
            log.error("error:during coordinate x setting, wrong format");
            return false;

        }
        if (test.length() > 15) {
            System.err.println("error:during coordinate x setting,value is too large");
            log.error("error:during coordinate x setting,value is too large");
            return false;
        }
        return true;
    }

    public boolean checkCoordinateY(String test) {
        if (!test.matches(FLOAT_NUMBER_FORMAT)) {
            System.err.println("error:during coordinate y setting");
            log.error("error:during coordinate y setting");
            System.err.println("value is whole or fractional number");

            return false;

        }
        if (test.length() > 15) {
            System.err.println("error:during coordinate y setting");
            System.err.println("value is too large");
            log.error("error:during coordinate y setting value is too large");

            return false;
        }
        return true;
    }

    public boolean checkLocationCoordinateX(String test) {
        if (!test.matches(FLOAT_NUMBER_FORMAT)) {
            System.err.println("error:during location coordinate x setting");
            System.err.println("value is whole or fractional number");

            log.error("error:during location coordinate x setting,wrong format");

            return false;

        }
        if (test.length() > 15) {
            System.err.println("error:during location coordinate x setting");
            System.err.println("value is too large");
            log.error("error:during location coordinate x setting value is too large");

            return false;
        }
        return true;
    }

    public boolean checkLocationCoordinateY(String test) {

        if (!test.matches(FLOAT_NUMBER_FORMAT)) {
            System.err.println("error:during location coordinate y setting");
            System.err.println("value is whole or fractional number");
            log.error("error:during location coordinate y setting wrong format");

            return false;
        }
        if (test.length() > 15) {
            System.err.println("error:during location coordinate y setting");
            System.err.println("value is too large");
            log.error("error:during location coordinate y setting value is too large");

            return false;
        }
        return true;
    }

    public boolean checkId(String test) {
        if (!test.matches(POSITIVE_NUMBER_FORMAT)) {
            System.err.println("error:id has wrong format");
            System.err.println("id is natural number");
            log.error("error:id has wrong format");

            return false;
        }
        Long id = Long.parseLong(test);
        if (Command.controller.isPersonExist(id)) {
            System.err.println("error:id has wrong format");
            System.err.println("person with  id " + id + " already exist");
            log.error("error:id has wrong format");

            return false;
        }

        return true;
    }

    public boolean checkLocationCoordinateZ(String test) {

        if (!test.matches(FLOAT_NUMBER_FORMAT)) {
            System.err.println("error:during location coordinate z setting");
            log.error("error: error:during location coordinate z setting");
            System.err.println("value is whole or fractional number");

            return false;
        }
        if (test.length() > 15) {
            System.err.println("error:during location coordinate z setting");
            log.error("error: error:during location coordinate z setting,value is too large");
            System.err.println("value is too large");
            return false;
        }
        return true;
    }

    public boolean checkHeight(String test) {
        if (!test.matches(POSITIVE_FLOAT_NUMBER_FORMAT)) {
            System.err.println("error:during height setting");
            log.error("error:during height setting");
            System.err.println("value is positive whole or fractional number");
            return false;
        }
        if (test.length() > 15) {
            System.err.println("error:during height setting");
            log.error("error:during height setting");
            System.err.println("value is too large");
            return false;
        }
        return true;
    }

    public boolean checkCreationTime(String test) {

        if (!test.matches(CREATION_TIME_FORMAT)) {
            System.err.println("error:creation time has wrong format");
            return false;
        }
        return true;
    }

    public boolean checkName(String test) {

        if (test.length() > 40) {
            System.err.println("error during name setting:too long line");
            System.err.println("maximum is 40 letters");
            log.error("error during name setting:too long line");
            return false;
        }
        if (!test.matches(NAME_FORMAT)) {
            System.err.println("error during name setting:line has wrong format");
            System.err.println("use only digits,letters,and dash for double name");
            log.error("error during name setting:line has wrong format ");
            return false;
        }
        return true;
    }
}
