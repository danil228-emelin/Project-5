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
    private final static String SUCCESS = "Command Clear deleted elements ";

    /**
     * Command clear,clear collection  after execution size=0
     */
    @Override

    public String execute() {
        controller.getPersonList().clear();
        PersonReadingBuilder.setId(1L);

        return SUCCESS;
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
