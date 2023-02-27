package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.parser.Parser;
import itmo.p3108.util.CheckData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * save elements of collection in xml file
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Save implements IndependentCommand, NoArgumentCommand {


    @Override
    public String execute() {
        try {
            Parser.write(CheckData.getPath());
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
