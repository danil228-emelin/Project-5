package itmo.p3108.command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.parser.Parser;
import itmo.p3108.util.FileValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * Command  save,save elements of collection in xml file
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SuppressWarnings("unused")
public class Save implements IndependentCommand, NoArgumentCommand {
    /**
     * Saving collection by using class  @see {@link Parser}.
     * It parses collection xml format
     */
    private final static String SUCCESS = "Command save:collection is saved";
    private final static String FAIL_ERROR = "Error during saving elements";

    @Override
    public String execute() {
        try {
            Parser.write(new FileValidator().getPath());
            return SUCCESS;
        } catch (JAXBException | FileNotFoundException e) {
            log.error(e.getMessage());
            return FAIL_ERROR;
        }
    }

    @Override
    public String description() {
        return "save : сохранить коллекцию в файл";
    }

    @Override
    public String name() {
        return "save";
    }
}
