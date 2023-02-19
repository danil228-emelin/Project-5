package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.PersonReadingBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * clear collection
 */
@Slf4j
public class Clear implements NoArgumentCommand {
    private static Clear clear;

    private Clear() {
    }

    public static Clear getInstance() {

        if (clear == null) {
            clear = new Clear();
            log.info("Clear initialized");
        }
        return clear;
    }

    /**
     * call clear function
     */
    @Override

    public String execute() {
        controller.getPersonList().clear();
        PersonReadingBuilder.setId(1L);
        log.info("Command Clear deleted elements  ");

        return "elements are deleted ";
    }

    @Override
    public String description() {
        return "    clear : очистить коллекцию";
    }

    @Override
    public String name() {
        return "clear";
    }
}
