package itmo.p3108.command;

import com.sun.istack.Nullable;
import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.util.Reflection;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Class FlyWeightCommandFactory generated all commands
 * and control that only one instance of each command was created
 */
@Slf4j
public class FlyWeightCommandFactory {
    private static final FlyWeightCommandFactory FLY_WEIGHT_COMMAND = new FlyWeightCommandFactory();
    private final HashMap<String, Command> COMMAND_MAP = new HashMap<>();

    /**
     * in constructor  reflections is used  to find all commands in project
     */
    private FlyWeightCommandFactory() {
        Set<Class<?>> set = Reflection.findAllCommands("itmo.p3108.command");
        if (set == null) {
            throw new ValidationException("Commands not found");
        }
        for (Class<?> commandClass : set) {
            try {
                Object object = commandClass.getConstructor().newInstance();
                if (object instanceof Command command) {
                    COMMAND_MAP.put(command.name(), command);
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                     InstantiationException exception) {
                log.error(exception.getMessage());
            }
        }
    }

    public static FlyWeightCommandFactory getInstance() {
        return FLY_WEIGHT_COMMAND;
    }


    /**
     * @param key name of command
     * @return command if it exists
     */
    @Nullable
    public Command getCommand(String key) {
        if (COMMAND_MAP.containsKey(key.trim().toLowerCase())) {
            return COMMAND_MAP.get(key.trim().toLowerCase());
        }

        return null;
    }

    /**
     * @return all commands which contains factory
     */
    public Collection<Command> getValues() {
        return COMMAND_MAP.values();
    }

}