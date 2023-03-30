package itmo.p3108.command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Command exit,exit without saving collection
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SuppressWarnings("unused")
public class Exit implements IndependentCommand, NoArgumentCommand {
    /**
     * @return the result of command
     */
    @Override
    public String execute() {
        log.warn(" command didn't save collection before exit");

        System.exit(0);

        return "";
    }

    @Override
    public String description() {
        return "exit : завершить программу (без сохранения в файл)";
    }

    @Override
    public String name() {
        return "exit";
    }
}
