package itmo.p3108.util;

import com.sun.istack.Nullable;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Reflection {
    private Reflection() {
    }

    /**
     * @param packageName source package
     * @return find all commands in certain package
     */
    @Nullable
    public static Set<Class<?>> findAllClasses(String packageName, Class<?> classExtended) {
        try {

            Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
            return reflections.getSubTypesOf(classExtended)
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
    @Nullable
    public static Set<Method> findAllClassesWithAnnotation(String pathToCheckedClass, Class<? extends Annotation> annotation) {
        try {


            Reflections reflections = new Reflections(pathToCheckedClass, new MethodAnnotationsScanner());


            return reflections.getMethodsAnnotatedWith(annotation);

        } catch (ReflectionsException exception) {
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @Nullable
    public static Method findMethodInClass(Class<?> certainClass, String name, Class<? extends Annotation> annotation) {
        Method[] methods = certainClass.getMethods();
        for (Method method : methods) {
            if (method.getName().toLowerCase().contains(name.toLowerCase()) && method.isAnnotationPresent(annotation)) {
                return method;
            }
        }
        return null;


    }
}
