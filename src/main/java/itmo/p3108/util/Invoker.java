package itmo.p3108.util;

import itmo.p3108.command.FlyWeightCommand;
import itmo.p3108.command.one_argument_command.*;
import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.exception.ValidationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * invoke commands
 */
@Slf4j
public class Invoker {
    private static final Invoker invoker = new Invoker();
    private final HashMap<String, Command> commands = new HashMap<>();
    private final List<String> executeScriptPaths = new ArrayList<>();

    private Invoker() {

        FlyWeightCommand.getInstance().getValues().forEach(this::add);
    }

    public static Invoker getInstance() {
        return invoker;
    }

    public Collection<Command> commands() {
        return commands.values();
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
     *
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
                throw new ValidationException("Error during execution command doesn't exist \n use help command");
            }
            Command command = commands.get(strings[0].toLowerCase());

            if (command instanceof NoArgumentCommand) {
                if (strings.length > 1) {
                    log.error("Error during execution command " + command.name() + " doesn't have arguments");
                    throw new ValidationException("Error during execution command " + command.name() + " doesn't have arguments");
                }

            } else if (strings.length > 2 || strings.length == 1) {
                log.error("Error during execution command " + command.name() + " has one argument ");
                throw new ValidationException("Error during execution command " + command.name() + " has one argument ");
            }

            if (command instanceof IndependentCommand) {
                if (command instanceof CheckData.SetPath) {
                    ((CheckData.SetPath) command).setPath(strings[1]);
                }
                System.out.println(command.execute());
                return;
            }

            try {
                if (command instanceof FilterStartsWithName) {
                    ((FilterStartsWithName) command).setSubstring(strings[1]);
                    System.out.println(command.execute());
                    return;
                }
                if (command instanceof Update) {
                    Long l = Long.parseLong(strings[1]);
                    ((Update) command).findPerson(l);
                    System.out.println(command.execute());
                    return;
                }

                if (command instanceof RemoveById) {
                    Long l = Long.parseLong(strings[1]);
                    ((RemoveById) command).setId(l);
                    System.out.println(command.execute());
                    return;
                }

                if (command instanceof CountByHeight) {
                    double l = Double.parseDouble(strings[1]);
                    ((CountByHeight) command).setHeight(l);
                    System.out.println(command.execute());
                    return;
                }

            } catch (NumberFormatException e) {
                log.error("Error during execution command CountByHeight:number has wrong format ");
                System.err.println("Error during execution command CountByHeight:number has wrong format ");
            }
            if (command instanceof ExecuteScript) {
                if (executeScriptPaths.contains(strings[1])) {
                    log.error("Error : execute_script can't be executed "
                            + "(" + strings[1] + ")" + ".Recursion is forbidden");
                    System.err.println("Error : execute_script can't be executed "
                            + "(" + strings[1] + ")" + ".Recursion is forbidden");
                    return;
                }
                if (Files.exists(Path.of(strings[1]))) {
                    executeScriptPaths.add(strings[1]);
                    ((ExecuteScript) command).setPath(strings[1]);
                    System.out.println(command.execute());
                    return;
                } else {
                    log.error("Error during execution command :file " + strings[1] + " doesn't exist");
                    throw new ValidationException("Error during execution command :file " + strings[1] + " doesn't exist");
                }
            }
            if (command instanceof NoArgumentCommand) {
                System.out.println(command.execute());
            }

            if (Command.controller.isEmpty()) {
                log.error("Collection is empty");
                System.out.println("Collection is empty");
            }
        } catch (ValidationException e) {
            log.error(e.getMessage());
            System.err.println(e.getMessage());
        }

    }
}

