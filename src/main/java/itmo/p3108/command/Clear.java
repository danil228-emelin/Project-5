package itmo.p3108.command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.model.PersonReadingBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Command Clear,clear collection
 * don't save elements before cleaning
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SuppressWarnings("unused")
public class Clear implements NoArgumentCommand {
    /**
     * Command clear,clear collection  after execution size=0
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
        return "clear : очистить коллекцию";
    }

    @Override
    public String name() {
        return "clear";
    }
}
