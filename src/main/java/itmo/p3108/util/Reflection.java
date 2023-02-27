package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Set;
import java.util.stream.Collectors;

public class Reflection {
    private Reflection() {
    }

    public static Set<Class<?>> findAllCommands(String packageName) {
        org.reflections.Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(Command.class)
                .stream()
                .parallel()
                .collect(Collectors.toSet());

    }
}
