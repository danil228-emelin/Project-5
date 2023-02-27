package itmo.p3108;

import itmo.p3108.parser.Parser;
import itmo.p3108.util.FileValidator;
import itmo.p3108.util.Invoker;
import itmo.p3108.util.UserReader;
import lombok.extern.slf4j.Slf4j;

/**
 * main class
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

