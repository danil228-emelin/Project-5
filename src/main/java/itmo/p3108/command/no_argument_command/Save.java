package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.parser.Parser;
import itmo.p3108.util.CheckFail;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

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
            Parser.write(CheckFail.getPath());
            return "коллекция сохранена";
        } catch (JAXBException | FileNotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String name() {
        return "save";
    }
}
