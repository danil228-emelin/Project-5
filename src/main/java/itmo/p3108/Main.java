package itmo.p3108;

import itmo.p3108.parser.Parser;
import itmo.p3108.util.CheckFail;
import itmo.p3108.util.Invoker;
import itmo.p3108.util.InvokerFactory;
import itmo.p3108.util.UserReader;

/**
 * main class
 */
public class Main {
    public static void main(String[] args) {


       CheckFail.execute();
        Parser.read(CheckFail.getPath());
//
        Invoker invoker = InvokerFactory.createInvoker();

        while (true) {
            invoker.invoke(UserReader.read());
       }

    }
}

