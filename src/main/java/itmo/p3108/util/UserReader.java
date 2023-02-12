package itmo.p3108.util;

import java.util.Scanner;

final public class UserReader {
    private UserReader(){}
private static final Scanner scanner =new Scanner(System.in);

public static String  read(){
    System.out.print("$ ");
    return scanner.nextLine();
}
}
