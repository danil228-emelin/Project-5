package itmo.p3108;

import itmo.p3108.parser.Parser;
import itmo.p3108.util.FileValidator;
import itmo.p3108.util.Invoker;
import itmo.p3108.util.UserReader;
import lombok.extern.slf4j.Slf4j;

/**
 * Entry point of program
 * Before working with user,
 * FileValidator check whether the xml file for serialization and deserialization has added
 * Parser try to download elements from xml file
 * Invoker using getInstance download all existing commands.
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        FileValidator fileValidator = new FileValidator();
        String path = fileValidator.findFile();
        Parser.read(path);
        Invoker invoker = Invoker.getInstance();
        while (true) {
            invoker.invoke(UserReader.read());
        }

    }
}

