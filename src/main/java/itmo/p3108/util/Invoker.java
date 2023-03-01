package itmo.p3108.util;

import itmo.p3108.command.*;
import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.exception.CommandException;
import itmo.p3108.exception.FileException;
import itmo.p3108.exception.ValidationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class invoker,invoke and analyze commands
 */
@Slf4j
public class Invoker {
    private static final Invoker invoker = new Invoker();
    private final HashMap<String, Command> commands = new HashMap<>();
    private final HashSet<String> executeScriptPaths = new HashSet<>();

    private Invoker() {
        FlyWeightCommandFactory.getInstance().getValues().forEach(this::add);
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
     * analyzing for different conditions  and then try to invoke command
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

                throw new CommandException("Error during execution command doesn't exist \n use help command");

            }
            Command command = commands.get(strings[0].toLowerCase());

            if (command instanceof NoArgumentCommand) {
                if (strings.length > 1) {
                    log.error("Error during execution command " + command.name() + " doesn't have arguments");
                    throw new CommandException("Error during execution command " + command.name() + " doesn't have arguments");
                }

            } else if (strings.length > 2 || strings.length == 1) {
                log.error("Error during execution command " + command.name() + " has one argument ");
                throw new CommandException("Error during execution command " + command.name() + " has one argument ");
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
                if (executeScriptPaths.size() > 49) {
                    throw new FileException("Too many files in executed_script");
                }
                String path = strings[1].toLowerCase();
                if (executeScriptPaths.contains(path)) {
                    log.error(String.format("Error : execute_script can't be executed %s.Recursion is forbidden", strings[1]));
                    System.err.printf("Error : execute_script can't be executed %s.Recursion is forbidden\n", strings[1]);
                    return;
                }
                if (Files.exists(Path.of(path))) {
                    executeScriptPaths.add(path);
                    ((ExecuteScript) command).setPath(path);
                    System.out.println(command.execute());
                    return;
                } else {
                    log.error("Error during execution command :file " + strings[1] + " doesn't exist");
                    throw new CommandException("Error during execution command :file " + strings[1] + " doesn't exist");
                }
            }
            if (command instanceof NoArgumentCommand) {
                System.out.println(command.execute());
                return;
            }

            if (Command.controller.isEmpty()) {
                log.error("Collection is empty");
                throw new ValidationException("Collection is empty");
            }
        } catch (ValidationException | CommandException|FileException e) {
            log.error(e.getMessage());
            System.err.println(e.getMessage());
        }

    }
}

