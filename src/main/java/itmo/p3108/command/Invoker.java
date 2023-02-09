package itmo.p3108.command;

import itmo.p3108.exception.ValidationException;
import lombok.NonNull;

import java.util.HashMap;

public class Invoker {
    private static Invoker invoker;
    private final HashMap<String, Command> commands = new HashMap<>();

    private Invoker() {
    }

    public static Invoker getInstance() {
        if (invoker == null) {
            invoker = new Invoker();
        }

        return invoker;
    }

    public void add(@NonNull Command... commands) {
        for (Command command : commands) {
            if (!this.commands.containsKey(command.name())) {
                this.commands.put(command.name(), command);
            }
        }
    }

    public void invoke(String commandStr) {
        if (commandStr.length() != 0) {
            String[] strings = commandStr.trim().split(" ");
            if (commands.containsKey(strings[0].toLowerCase())) {
                Command command = commands.get(strings[0].toLowerCase());

                if (command instanceof NoArgumentCommand) {
                    if (strings.length > 1) {
                        throw new ValidationException("Команда " + command.name() + " не имеет аргументов");
                    } else {

                        System.out.println(command.execute());
                    }
                }
            } else {
                throw new ValidationException("Такой команды не существует");
            }
        }
    }
}
