package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.model.Color;
import itmo.p3108.model.Country;
import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * set path  file for serialization
 */
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
            System.err.println("no rights for writing and reading:" + path);
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
        System.out.println("file is set successfully");
    }

    public static boolean checkCreationTime(String test) {
        if (!test.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z")) {
            System.err.println("error:creation time has wrong format");
            System.err.println(test +" incorrect");
            System.err.println(test +" is incorrect");


            return false;
        }
        return true;
    }

    public static boolean checkName(String test) {

        if (test.length() > 40) {
            System.err.println("error:too long line");
            System.err.println("during name setting");

            return false;
        }
        if (!test.matches("(\\w+-?\\w*)")) {
            System.err.println("error:line has wrong format");
            System.err.println("during name setting");
            System.err.println(test +" is incorrect");


            return false;
        }
        return true;
    }

    public static boolean checkBirthday(String test) {
        if (!test.matches("\\d{2}-\\d{2}-\\d{4}")) {
            System.err.println("error:during birthday setting");
            System.err.println("line has wrong format");
            System.err.println(test +" is incorrect");

            return false;
        }
        String[] strings = test.split("-");

        if (Integer.parseInt(strings[0]) > 12 || Integer.parseInt(strings[1]) > 31
                || Integer.parseInt(strings[2]) > 2024 || Integer.parseInt(strings[2]) < 1920) {
            System.err.println("error:during birthday setting");
            System.err.println("value is incorrect");
            System.err.println(test +" is incorrect");

            return false;
        }
        return true;
    }


    public static boolean checkNationalityReadingFromConsole(String test) {
        if (!test.matches("[1-4]")) {
            System.err.println("error:during nationality setting");

            System.err.println("line has wrong format");
            System.err.println(test +" is incorrect");

            return false;
        }
        return true;
    }

    public static boolean checkNationalityReadingFromFile(String test) {

        if (!Country.isPresent(test)) {
            System.err.println("error:during nationality setting");
            System.err.println("line has wrong format");
            System.err.println(test +" is incorrect");
            return false;
        }
        return true;
    }

    public static boolean checkCoordinateX(String test) {
        if (!test.matches("-?\\d+")) {
            System.err.println("error:during coordinate x setting");
            System.err.println("wrong format for x");
            System.err.println(test +" is incorrect");

            return false;

        }
        if (test.length() > 15) {
            System.err.println("error:during coordinate x setting");
            System.err.println("value is too large");
            System.err.println(test +" is incorrect");

            return false;
        }
        return true;
    }

    public static boolean checkCoordinateY(String test) {
        if (!test.matches("-?\\d+\\.?\\d*")) {
            System.err.println("error:during coordinate y setting");

            System.err.println("value is whole or fractional number");
            System.err.println(test +" is incorrect");

            return false;

        }
        if (test.length() > 15) {
            System.err.println("error:during coordinate y setting");
            System.err.println("value is too large");
            System.err.println(test +" is incorrect");

            return false;
        }
        return true;
    }

    public static boolean checkLocationCoordinateX(String test) {
        if (!test.matches("-?\\d+\\.?\\d+") && !test.matches("-?\\d+")) {
            System.err.println("error:during location coordinate x setting");
            System.err.println("wrong format");
            System.err.println(test +" is incorrect");

            return false;

        }
        if (test.length() > 15) {
            System.err.println("error:during location coordinate x setting");
            System.err.println("value is too large");
            System.err.println(test +" is incorrect");

            return false;
        }
        return true;
    }

    public static boolean checkLocationCoordinateY(String test) {

        if (!test.matches("-?\\d+\\.?\\d*")) {
            System.err.println("error:during location coordinate y setting");

            System.err.println("wrong format");
            System.err.println(test +" is incorrect");

            return false;
        }
        if (test.length() > 15) {
            System.err.println("error:during location coordinate y setting");

            System.err.println("value is too large");
            System.err.println(test +" is incorrect");

            return false;
        }
        return true;
    }

    public static boolean checkId(String test) {
        if (!test.matches("\\d+")) {
            System.err.println("error:id has wrong format");
            System.err.println(test +" is incorrect");

            return false;
        }
        return true;
    }

    public static boolean checkLocationCoordinateZ(String test) {

        if (!test.matches("-?\\d+\\.?\\d*")) {
            System.err.println("error:during location coordinate z setting");

            System.err.println("wrong format");
            System.err.println(test +" is incorrect");

            return false;
        }
        if (test.length() > 15) {
            System.err.println("error:during location coordinate z setting");

            System.err.println("value is too large");
            System.err.println(test +" is incorrect");

            return false;
        }
        return true;
    }

    public static boolean checkColourReadingConsole(String test) {
        if (!test.matches("[1-5]")) {
            System.err.println("error:during colour setting");
            System.err.println("line has wrong format");
            System.err.println(test +"is  incorrect line ");

            return false;
        }
        return true;
    }

    public static boolean checkColourReadingFile(String test) {
        if (!Color.isPresent(test)) {
            System.err.println("error:during colour setting");
            System.err.println("line has wrong format");
            System.err.println(test +" incorrect");
            return false;
        }
        return true;
    }

    public static boolean checkHeight(String test) {
        if (!test.matches("\\d+\\.?\\d*")) {
            System.err.println("error:during height setting");

            System.err.println("value is positive whole or fractional number");
            System.err.println(test +" is incorrect");

            return false;
        }
        if (test.length() > 15) {
            System.err.println("error:during height setting");

            System.err.println("value is too large");
            System.err.println(test +" is incorrect");

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
                return "file " + testPath + " is set";
            }
            System.err.println("file " + testPath + " can't be set");
            return "";

        }

        public String name() {
            return "set_path";
        }
    }

}
