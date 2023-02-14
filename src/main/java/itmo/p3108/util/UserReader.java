package itmo.p3108.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

final public class UserReader {
    private static final Scanner scanner = new Scanner(System.in);

    private UserReader() {
    }

    public static String read() {
        System.out.print("$ ");
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Досрочный выход из программы");
            System.exit(-1);
            return "";
        }
    }
}
