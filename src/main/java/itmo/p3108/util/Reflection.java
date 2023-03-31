package itmo.p3108.util;

import com.sun.istack.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.ReflectionsException;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class Reflection,provide Reflections class functionality
 */
@Slf4j
public class Reflection {
    private Reflection() {
    }

    /**
     * @param packageName source package
     * @return find all commands in certain package
     */
    @Nullable
    public static Optional<Set<Class<?>>> findAllClasses(String packageName, Class<?> classExtended) {
        try {

            Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
            return Optional.of(reflections.getSubTypesOf(classExtended).stream().parallel().collect(Collectors.toSet()));

        } catch (ReflectionsException exception) {
            System.err.println(exception.getMessage());
        }
        return Optional.empty();
    }

    @Nullable
    public static Optional<Set<Class<?>>> findAllAnnotatedClasses(String packageName, Class<? extends Annotation> annotation) {
        try {

            Reflections reflections = new Reflections(packageName, new TypeAnnotationsScanner(), new SubTypesScanner(false));
            return Optional.of(reflections.getTypesAnnotatedWith(annotation).stream().parallel().collect(Collectors.toSet()));

        } catch (ReflectionsException exception) {
            System.err.println(exception.getMessage());
        }
        return Optional.empty();
    }

    /**
     * @param pathToCheckedClass source package
     * @param annotation         certain annotation to find
     * @return commands which has certain annotation
     */
    @Nullable
    public static Optional<Set<Method>> findAllMethodsWithAnnotation(String pathToCheckedClass, Class<? extends Annotation> annotation) {
        try {


            Reflections reflections = new Reflections(pathToCheckedClass, new MethodAnnotationsScanner());


            return Optional.of(reflections.getMethodsAnnotatedWith(annotation));

        } catch (ReflectionsException exception) {
            System.err.println(exception.getMessage());
        }
        return Optional.empty();
    }

    public static Field[] findAllFields(Class<?> someClass) {
        return someClass.getDeclaredFields();
    }
}
