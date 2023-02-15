package itmo.p3108.util;

import itmo.p3108.command.one_argument_command.*;
import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.exception.ValidationException;
import lombok.NonNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * invoke commands
 */
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

    /**
     * invoke try to do command
     * @param commandStr analyze for different conditions
     */
    public void invoke(String commandStr) {
        try {
            if (commandStr.equals("")) {
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

                throw new ValidationException("command doesn't exist "+strings[0]);
            }
            Command command = commands.get(strings[0].toLowerCase());

            if (command instanceof NoArgumentCommand) {
                if (strings.length > 1) {

                    throw new ValidationException("command " + command.name() + " doesn't have arguments");
                }

            } else if (strings.length > 2 || strings.length == 1) {
                throw new ValidationException("command " + command.name() + " has one argument ");
            }

            if (command instanceof IndependentCommand ) {
                if (command instanceof CheckFail.SetPath) {
                    ((CheckFail.SetPath) command).setPath(strings[1]);
                }
                System.out.println(command.execute());
                return;
            }
            if (Command.controller.isEmpty()) {
                throw new ValidationException("command " + command.name() + " can't be executed,collection is empty");
            }


            if (command instanceof FilterStartsWithName) {

                try {

                    ((FilterStartsWithName) command).setSubstring(strings[1]);
                    System.out.println(command.execute());
                    return;
                } catch (NumberFormatException e) {
                    System.err.println("error:wrong format");
                }

            }


            if (command instanceof Update) {

                try {
                    Long l = Long.parseLong(strings[1]);

                    ((Update) command).findPerson(l);
                    System.out.println(command.execute());
                    return;
                } catch (NumberFormatException e) {
                    System.err.println("error:wrong format");
                }

            }

            if (command instanceof RemoveById) {

                try {
                    Long l = Long.parseLong(strings[1]);
                    ((RemoveById) command).setId(l);
                    System.out.println(command.execute());
                    return;
                } catch (NumberFormatException e) {
                    System.err.println("error:wrong format");
                }

            }

            if (command instanceof CountByHeight) {

                try {
                    double l = Double.parseDouble(strings[1]);
                    ((CountByHeight) command).setHeight(l);
                    System.out.println(command.execute());
                    return;
                } catch (NumberFormatException e) {
                    System.err.println("error:wrong format");
                }

            }
            if (command instanceof ExecuteScript) {
                if (executeScriptPaths.contains(strings[1])) {
                    throw new ValidationException("error: execute_script can't be executed "
                            +"("+strings[1]+")"+ ".Recursion is forbidden");
                }
                if (Files.exists(Path.of(strings[1]))) {
                    executeScriptPaths.add(strings[1]);
                    ((ExecuteScript) command).setPath(strings[1]);
                    System.out.println(command.execute());
                } else {
                    throw new  ValidationException("file " + strings[1] + " doesn't exist");
                }
            }
            if (command instanceof NoArgumentCommand) {
                System.out.println(command.execute());
            }


        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }

    }
}

