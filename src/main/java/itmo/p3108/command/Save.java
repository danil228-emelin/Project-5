package itmo.p3108.command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.parser.Parser;
import itmo.p3108.util.CreateFail;

public class Save implements NoArgumentCommand {
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
        Parser.write(CreateFail.getPath());
        return "коллекция сохранена";
    }

    @Override
    public String name() {
        return "save";
    }
}
