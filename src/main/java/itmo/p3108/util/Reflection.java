package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import org.reflections.Reflections;
import org.reflections.ReflectionsException;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class Reflection,provide Reflections class functionality
 */
public class Reflection {
    private Reflection() {
    }

    /**
     * @param packageName source package
     * @return find all commands in certain package
     */
    public static Set<Class<?>> findAllCommands(String packageName) {
        try {


            Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
            return reflections.getSubTypesOf(Command.class)
                    .stream()
                    .parallel()
                    .collect(Collectors.toSet());

        } catch (ReflectionsException exception) {
            System.err.println(exception.getMessage());
        }
        return null;
    }

    /**
     * @param pathToCheckedClass source package
     * @param annotation         certain annotation to find
     * @return commands which has certain annotation
     */
    public static Set<Method> findAllCommandsWithAnnotation(String pathToCheckedClass, Class<? extends Annotation> annotation) {
        try {


            Reflections reflections = new Reflections(pathToCheckedClass, new MethodAnnotationsScanner());


            return reflections.getMethodsAnnotatedWith(annotation);

        } catch (ReflectionsException exception) {
            System.err.println(exception.getMessage());
        }
        return null;
    }

}
