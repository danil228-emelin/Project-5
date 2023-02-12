package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.CreationCommand;
import itmo.p3108.command.type.InformationCommand;
import itmo.p3108.command.type.NoArgumentCommand;
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
        if (commandStr.length() == 0) {
            return;
        }
        String[] strings = commandStr.trim().split("\\s+");
        if (!commands.containsKey(strings[0].toLowerCase())) {

            throw new ValidationException("Такой команды не существует");
        }
        Command command = commands.get(strings[0].toLowerCase());

        if (command instanceof NoArgumentCommand && strings.length > 1) {
            throw new ValidationException("Команда " + command.name() + " не имеет аргументов");
        }
        if (command instanceof InformationCommand || command instanceof CreationCommand) {
            System.out.println(command.execute());
            return;
        }
        if (Command.controller.isEmpty()) {
            throw new ValidationException("Команду " + command.name() + " невозможно выполнить,коллекция пустая");

        }
        System.out.println(command.execute());
    }
}

