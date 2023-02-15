package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.IndependentCommand;
import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * set path  file for serialization
 */
public class CheckFail {
    @Getter

    private static String path;

    private CheckFail() {
    }

    private static String readFileName() {
        String s = null;
        while (s == null) {
            System.out.println("enter file name with .xml for saving objects");
            String test = UserReader.read();
            if (!test.matches("\\w+\\.xml")) {
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
        if (!test.matches("[^~!/._].+\\.xml")) {
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
     * Set initial xml file  for serialization
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

    /**
     * set new xml file
     */
    public static class SetPath implements Command,IndependentCommand {
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
