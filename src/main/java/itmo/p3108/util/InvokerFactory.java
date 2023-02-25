package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Factory for invoker,add commands
 */
public class InvokerFactory {
    private InvokerFactory() {
    }

    public static Invoker createInvoker() {
        Invoker invoker = Invoker.getInstance();


        Reflections reflections = new Reflections("itmo.p3108.command", new SubTypesScanner(false));


        Set<Class<?>> set = reflections.getSubTypesOf(Object.class)
                .stream()
                .parallel()
                .collect(Collectors.toSet());

        for (Class<?> commandClass : set) {
            try {
                Method method = commandClass.getMethod("getInstance");
                Command command = (Command) method.invoke(null);
                invoker.add(command);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
            }

        }
        return invoker;
    }
}