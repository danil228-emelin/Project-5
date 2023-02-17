package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.model.Color;
import itmo.p3108.model.Country;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * set path  file for serialization
 */
@Slf4j
public class CheckData {
    @Getter

    private static String path;

    private CheckData() {
    }

    private static String readFileName() {
        String s = null;
        while (s == null) {
            System.out.println("enter file name  for saving objects");
            String test = UserReader.read();
            if (!test.matches("[^~!/._].+")) {
                log.error("error during read file name :wrong data format");
                System.err.println("error:wrong data format");
                continue;
            }
            s = test;
        }
        return s;

    }

    /**
     * check whether the file exist,has right for reading and writing,has right format
     */
    private static boolean fileCheck(String test) {
        if (!test.matches("[^~!/._].+")) {
            log.error("error during  file check :wrong data format");
            System.err.println("file has incorrect name");
            return false;
        }
        Path path = Paths.get(test);
        if (Files.notExists(path)) {
            return true;
        }
        if (Files.isWritable(path) && Files.isReadable(path))
            return true;
        else {
            log.error("Permission error:no rights for writing and reading:" + path);
            System.err.println("Permission error:no rights for writing and reading:" + path);
            return false;
        }

    }

    /**
     * Set initial  file  for serialization
     */
    public static void execute() {
        path = System.getenv("COLLECTION_PATH");
        if (path == null) {
            System.err.println("file for collection saving isn't specified");
            log.error("file for collection saving isn't specified");
            path = readFileName();
        } else {
            System.out.println("default file " + path);
        }
        boolean isFileAlright = false;
        while (!isFileAlright) {

            isFileAlright = fileCheck(path);
            if (!isFileAlright) {
                path = readFileName();
            }
        }
        log.info("file is set successfully");

        System.out.println("file is set successfully");
    }

    public static boolean checkCreationTime(String test) {
        log.error("error:creation time has wrong format " + test + " incorrect");

        if (!test.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z")) {
            System.err.println("error:creation time has wrong format");
            System.err.println(test + " incorrect");


            return false;
        }
        return true;
    }

    public static boolean checkName(String test) {

        if (test.length() > 40) {
            System.err.println("error during name setting:too long line");
            log.error("error during name setting:too long line");
            return false;
        }
        if (!test.matches("(\\w+-?\\w*)")) {
            System.err.println("error during name setting:line has wrong format");
            log.error("error during name setting:line has wrong format " + test);
            System.err.println(test + " is incorrect");
            return false;
        }
        return true;
    }

    public static boolean checkBirthday(String test) {
        if (!test.matches("\\d{2}-\\d{2}-\\d{4}")) {
            System.err.println("error:during birthday setting line has wrong format");
            log.error("error:during birthday setting line has wrong format");
            System.err.println(test + " is incorrect");

            return false;
        }
        String[] strings = test.split("-");

        if (Integer.parseInt(strings[0]) > 12 || Integer.parseInt(strings[1]) > 31
                || Integer.parseInt(strings[2]) > 2024 || Integer.parseInt(strings[2]) < 1920) {
            System.err.println("error:during birthday setting value is incorrect");
            log.error("error:during birthday setting value is incorrect");

            System.err.println(test + " is incorrect");

            return false;
        }
        return true;
    }


    public static boolean checkNationalityReadingFromConsole(String test) {
        if (!test.matches("[1-4]")) {
            System.err.println("error:during nationality setting line has wrong format");
            log.error("error:during nationality setting line has wrong format");
            System.err.println(test + " is incorrect");
            return false;
        }
        return true;
    }

    public static boolean checkNationalityReadingFromFile(String test) {

        if (!Country.isPresent(test)) {
            System.err.println("error:during nationality setting line has wrong format");
            log.error("error:during nationality setting line has wrong format");
            System.err.println(test + " is incorrect");
            return false;
        }
        return true;
    }

    public static boolean checkCoordinateX(String test) {
        if (!test.matches("-?\\d+")) {
            System.err.println("error:during coordinate x setting, wrong format");
            System.err.println(test + " is incorrect");
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

    public static boolean checkCoordinateY(String test) {
        if (!test.matches("-?\\d+\\.?\\d*")) {
            System.err.println("error:during coordinate y setting");
            log.error("error:during coordinate y setting");
            System.err.println("value is whole or fractional number");
            System.err.println(test + " is incorrect");

            return false;

        }
        if (test.length() > 15) {
            System.err.println("error:during coordinate y setting");
            System.err.println("value is too large");
            System.err.println(test + " is incorrect");
            log.error("error:during coordinate y setting value is too large");

            return false;
        }
        return true;
    }

    public static boolean checkLocationCoordinateX(String test) {
        if (!test.matches("-?\\d+\\.?\\d+") && !test.matches("-?\\d+")) {
            System.err.println("error:during location coordinate x setting");
            System.err.println("wrong format");
            System.err.println(test + " is incorrect");
            log.error("error:during location coordinate x setting,wrong format");

            return false;

        }
        if (test.length() > 15) {
            System.err.println("error:during location coordinate x setting");
            System.err.println("value is too large");
            System.err.println(test + " is incorrect");
            log.error("error:during location coordinate x setting value is too large");

            return false;
        }
        return true;
    }

    public static boolean checkLocationCoordinateY(String test) {

        if (!test.matches("-?\\d+\\.?\\d*")) {
            System.err.println("error:during location coordinate y setting");

            System.err.println("wrong format");
            System.err.println(test + " is incorrect");
            log.error("error:during location coordinate y setting wrong format");

            return false;
        }
        if (test.length() > 15) {
            System.err.println("error:during location coordinate y setting");

            System.err.println("value is too large");
            System.err.println(test + " is incorrect");
            log.error("error:during location coordinate y setting value is too large");

            return false;
        }
        return true;
    }

    public static boolean checkId(String test) {
        if (!test.matches("\\d+")) {
            System.err.println("error:id has wrong format");
            System.err.println(test + " is incorrect");
            log.error("error:id has wrong format");

            return false;
        }
        return true;
    }

    public static boolean checkLocationCoordinateZ(String test) {

        if (!test.matches("-?\\d+\\.?\\d*")) {
            System.err.println("error:during location coordinate z setting");
            log.error("error: error:during location coordinate z setting");

            System.err.println("wrong format");
            System.err.println(test + " is incorrect");

            return false;
        }
        if (test.length() > 15) {
            System.err.println("error:during location coordinate z setting");
            log.error("error: error:during location coordinate z setting,value is too large");

            System.err.println("value is too large");
            System.err.println(test + " is incorrect");

            return false;
        }
        return true;
    }

    public static boolean checkColourReadingConsole(String test) {
        if (!test.matches("[1-5]")) {
            System.err.println("error:during colour setting line has wrong format");
            log.error("error:during colour setting line has wrong format");
            System.err.println(test + "is  incorrect line ");

            return false;
        }
        return true;
    }

    public static boolean checkColourReadingFile(String test) {
        if (!Color.isPresent(test)) {
            System.err.println("error:during colour setting line has wrong format");
            log.error("error:during colour setting line has wrong format");

            System.err.println(test + " incorrect");
            return false;
        }
        return true;
    }

    public static boolean checkHeight(String test) {
        if (!test.matches("\\d+\\.?\\d*")) {
            System.err.println("error:during height setting");
            log.error("error:during height setting");
            System.err.println("value is positive whole or fractional number");
            System.err.println(test + " is incorrect");

            return false;
        }
        if (test.length() > 15) {
            System.err.println("error:during height setting");
            log.error("error:during height setting");
            System.err.println("value is too large");
            System.err.println(test + " is incorrect");

            return false;
        }
        return true;
    }


    /**
     * set new xml file
     */
    public static class SetPath implements Command, IndependentCommand {
        private static SetPath setPath;
        private String testPath;

        public static SetPath getInstance() {
            if (setPath == null) {
                setPath = new SetPath();
            }
            return setPath;
        }

        public void setPath(String failPath) {
            testPath = failPath;
        }

        @Override
        public String execute() {
            if (fileCheck(testPath)) {
                path = testPath;
               log.info("file " + testPath + " is set");
                return "file " + testPath + " is set";
            }
            log.error("file " + testPath + " can't be set");
            System.err.println("file " + testPath + " can't be set");
            return "";

        }

        public String name() {
            return "set_path";
        }
    }

}
