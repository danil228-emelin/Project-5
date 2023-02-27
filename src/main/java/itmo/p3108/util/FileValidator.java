package itmo.p3108.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileValidator {
    private final String FILE_NAME_FORMAT = "[^!_]+";
    @Getter
    private String path = System.getenv("COLLECTION_PATH");

    private boolean fileCheck(String test) {
        if (!test.matches(FILE_NAME_FORMAT)) {
            log.error("error during  file check  : name has wrong  format");
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
            log.error(String.format("Permission error:no rights for writing and reading:%s", path));
            System.err.printf("Permission error:no rights for writing and reading:%s%n", path);
            return false;
        }

    }

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
     * Set initial  file  for serialization
     */
    public String findFile() {
        if (path == null) {
            System.err.println("file for collection saving isn't specified");
            log.error("file for collection saving isn't specified");
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
        log.info("file is set");
        System.out.println("file is set successfully");
        return path;
    }
}
