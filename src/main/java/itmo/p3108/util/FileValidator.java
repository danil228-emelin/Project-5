package itmo.p3108.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class FileValidator,checking file attributes.
 * name,permission,
 */
@Slf4j

public class FileValidator {
    private final static String FAIL_NAME_ERROR = "error during  file check  : name has wrong  format";
    private final static String PERMISSION_ERROR = "Permission error:no rights for writing and reading";
    private final String FILE_NAME_FORMAT = "[^!_]+";
    @Getter
    private String path = System.getenv("COLLECTION_PATH");

    /**
     * @param test file name
     * @return return file with proper name
     */
    private boolean fileCheck(String test) {
        if (!test.matches(FILE_NAME_FORMAT)) {
            log.error(FAIL_NAME_ERROR);
            System.err.println(FILE_NAME_FORMAT);
            return false;
        }
        Path path = Paths.get(test);
        if (Files.notExists(path)) {
            return true;
        }
        if (Files.isWritable(path) && Files.isReadable(path))
            return true;
        else {
            log.error(String.format(PERMISSION_ERROR));
            System.err.printf(PERMISSION_ERROR);
            return false;
        }

    }

    /**
     * @return reading new file when previous file was rejected
     */
    private String readFileName() {
        String s = null;
        while (s == null) {
            System.out.println("enter file name  for saving objects");
            System.out.println("file can't start with \"^,~,!,/,.,_\"");
            String test = UserReader.read();
            if (!test.matches(FILE_NAME_FORMAT)) {
                log.error("error during read file name :wrong data format");
                System.err.println("error:wrong data format");
                continue;
            }
            s = test;
        }
        return s;

    }


    /**
     * Set initial  xml file for serialization and deserialization
     */
    public String findFile() {
        if (path == null) {
            System.err.println("file for collection saving isn't specified");
            log.error("file isn't specified");
            path = readFileName();
        } else {
            System.out.println("default file " + System.getenv("COLLECTION_PATH"));
        }
        boolean isFileAlright = false;
        while (!isFileAlright) {

            isFileAlright = fileCheck(path);
            if (!isFileAlright) {
                path = readFileName();
            }
        }
        System.out.println("file is set successfully");
        return path;
    }
}
