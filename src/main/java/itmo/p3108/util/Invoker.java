package itmo.p3108.util;

import itmo.p3108.command.*;
import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.exception.FileException;
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
 * Class invoker,invoke and analyze commands
 */
@Slf4j
public class Invoker {
    private static final Invoker invoker = new Invoker();
    private final HashMap<String, Command> commands = new HashMap<>();
    private final List<String> executeScriptPaths = new ArrayList<>();

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
            if (!commands.containsKey(commandStr.toLowerCase())) {
                throw new ValidationException("Error during execution command doesn't exist \n use help command" + strings[0]);
            }
            Command command = commands.get(commandStr.toLowerCase());

            if (command instanceof NoArgumentCommand) {
                if (strings.length > 1) {
                    log.error(String.format("Error during execution command  %s   doesn't have arguments", command.name()));
                    throw new ValidationException(String.format("Error during execution command %s doesn't have arguments", command.name()));
                }

            } else if (strings.length > 2 || strings.length == 1) {
                log.error(String.format("Error during execution command %s has one argument ", command.name()));
                throw new ValidationException(String.format("Error during execution command %s has one argument ", command.name()));
            }


            try {
                if (command instanceof FilterStartsWithName) {
                    ((FilterStartsWithName) command).setSubstring(strings[1]);
                    System.out.println(command.execute());
                    return;
                }
                if (command instanceof Update) {
                    Long id = Long.parseLong(strings[1]);
                    ((Update) command).findPerson(id);
                    System.out.println(command.execute());
                    return;
                }

                if (command instanceof RemoveById) {
                    Long id = Long.parseLong(strings[1]);
                    ((RemoveById) command).setId(id);
                    System.out.println(command.execute());
                    return;
                }

                if (command instanceof CountByHeight) {
                    double height = Double.parseDouble(strings[1]);
                    ((CountByHeight) command).setHeight(height);
                    System.out.println(command.execute());
                    return;
                }

            } catch (NumberFormatException e) {
                log.error("Error during execution command CountByHeight:number has wrong format ");
                System.err.println("Error during execution command CountByHeight:number has wrong format ");
            }

            if (command instanceof ExecuteScript) {
                if (executeScriptPaths.contains(strings[1])) {
                    log.error(String.format("Error : execute_script can't be executed(%s).Recursion is forbidden", strings[1]));
                    System.err.printf("Error : execute_script can't be executed(%s).Recursion is forbidden%n", strings[1]);
                    return;
                }
                if (Files.exists(Path.of(strings[1]))) {
                    executeScriptPaths.add(strings[1]);
                    ((ExecuteScript) command).setPath(strings[1]);
                    System.out.println(command.execute());
                    return;
                } else {

                    log.error(String.format("Error during execution command :file %s doesn't exist", strings[1]));
                    throw new FileException(String.format("Error during execution command :file %s doesn't exist", strings[1]));
                }
            }
            if (command instanceof NoArgumentCommand) {
                System.out.println(command.execute());
            }

            if (Command.controller.isEmpty()) {
                log.error("Collection is empty");
                System.out.println("Collection is empty");
            }
        } catch (ValidationException | FileException e) {
            log.error(e.getMessage());
            System.err.println(e.getMessage());
        }

    }
}

