package itmo.p3108.command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.util.Reflection;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

@Slf4j
public class FlyWeightCommand {
    private static final FlyWeightCommand FLY_WEIGHT_COMMAND = new FlyWeightCommand();
    private final HashMap<String, Command> COMMAND_MAP = new HashMap<>();

    private FlyWeightCommand() {
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

    public static FlyWeightCommand getInstance() {
        return FLY_WEIGHT_COMMAND;
    }

    public Command getCommand(String key) {
        if (COMMAND_MAP.containsKey(key.trim().toLowerCase())) {
            return COMMAND_MAP.get(key.trim().toLowerCase());
        }

        return null;
    }

    public Collection<Command> getValues() {
        return COMMAND_MAP.values();
    }

}