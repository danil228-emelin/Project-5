package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.parser.Parser;
import itmo.p3108.util.CheckData;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * save elements of collection in xml file
 */
public class Save implements IndependentCommand, NoArgumentCommand {
    private static Save save;

    private Save() {
    }

    public static Save getInstance() {
        if (save == null) {
            save = new Save();
        }
        return save;
    }

    @Override
    public String execute() {
        try {
            Parser.write(CheckData.getPath());
            return "collection is saved successfully";
        } catch (JAXBException | FileNotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String name() {
        return "save";
    }
}
