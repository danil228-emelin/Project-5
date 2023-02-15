package itmo.p3108;

import itmo.p3108.parser.Parser;
import itmo.p3108.util.CheckData;
import itmo.p3108.util.Invoker;
import itmo.p3108.util.InvokerFactory;
import itmo.p3108.util.UserReader;

/**
 * main class
 */
public class Main {
    public static void main(String[] args) {


       CheckData.execute();
        Parser.read(CheckData.getPath());
//
        Invoker invoker = InvokerFactory.createInvoker();

        while (true) {
            invoker.invoke(UserReader.read());
       }

    }
}

