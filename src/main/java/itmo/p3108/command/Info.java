package itmo.p3108.command;

import itmo.p3108.command.type.InformationCommand;
import itmo.p3108.command.type.NoArgumentCommand;

public class Info implements NoArgumentCommand, InformationCommand {
    private static Info info;

    private Info() {
    }

    public static Info getInstance() {
        if (info == null) {
            info = new Info();
        }
        return info;
    }

    @Override
    public String execute() {
        return controller.info();
    }

    @Override
    public String name() {
        return "info";
    }
}
