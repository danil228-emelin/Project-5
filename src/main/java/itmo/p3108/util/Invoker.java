package itmo.p3108.util;

import itmo.p3108.command.one_argument_command.*;
import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.exception.ValidationException;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Invoker {
    private static Invoker invoker;
    private final HashMap<String, Command> commands = new HashMap<>();
    private List<String> executeScriptPaths = new ArrayList<>();

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
        try {
            if (commandStr.equals("")) {
                return;
            }
            System.out.println("Введите n для отмены операции");
            String p = UserReader.read();
            if (p.equals("n")) {
                return;
            }
            String[] strings;
            if (commandStr.contains("\"")) {
                strings = commandStr.split("\"");
                String s = strings[0];
                strings[0] = s.trim();
            } else {
                strings = commandStr.trim().split("\\s+");
            }
            if (!commands.containsKey(strings[0].toLowerCase())) {

                throw new ValidationException("Такой команды не существует");
            }
            Command command = commands.get(strings[0].toLowerCase());

            if (command instanceof NoArgumentCommand) {
                if (strings.length > 1) {

                    throw new ValidationException("Команда " + command.name() + " не имеет аргументов");
                }

            } else if (strings.length > 2 || strings.length == 1) {
                throw new ValidationException("Команда " + command.name() + " имеет один аргумент ");
            }

            if (command instanceof IndependentCommand) {
                System.out.println(command.execute());
                return;
            }
            if (Command.controller.isEmpty()) {
                throw new ValidationException("Команду " + command.name() + " невозможно выполнить,коллекция пустая");
            }
            if (command instanceof NoArgumentCommand) {
                System.out.println(command.execute());
            }

            if (command instanceof FilterStartsWithName) {

                try {

                    ((FilterStartsWithName) command).setSubstring(strings[1]);
                    System.out.println(command.execute());
                    return;
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка:строка имела неверный формат");
                    e.printStackTrace();
                }

            }


            if (command instanceof Update) {

                try {
                    Long l = Long.parseLong(strings[1]);

                    ((Update) command).findPerson(l);
                    System.out.println(command.execute());
                    return;
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка:строка имела неверный формат");
                }

            }

            if (command instanceof RemoveById) {

                try {
                    Long l = Long.parseLong(strings[1]);
                    ((RemoveById) command).setId(l);
                    System.out.println(command.execute());
                    return;
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка:строка имела неверный формат");
                    e.printStackTrace();
                }

            }

            if (command instanceof CountByHeight) {

                try {
                    double l = Double.parseDouble(strings[1]);
                    ((CountByHeight) command).setHeight(l);
                    System.out.println(command.execute());
                    return;
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка:строка имела неверный формат");
                }

            }
            if (command instanceof ExecuteScript) {
                if (executeScriptPaths.contains(strings[1])) {
                    throw new ValidationException("Пошел нахуй,рекурсии не будет");
                }
                executeScriptPaths.add(strings[1]);
                ((ExecuteScript) command).setPath(strings[1]);
                System.out.println(command.execute());
            }
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
    }
}

