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

    @Override
    public String execute() {
        try {
            Parser.write(new FileValidator().getPath());
            log.info("Command save:collection is saved");
            return "collection is saved";
        } catch (JAXBException | FileNotFoundException e) {
            log.error("Error during saving elements,check or set new  file");
            return "Error during saving elements,check or set new  file";
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
