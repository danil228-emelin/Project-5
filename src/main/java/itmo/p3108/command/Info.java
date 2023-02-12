package itmo.p3108.command;

import itmo.p3108.command.type.NoArgumentCommand;
import itmo.p3108.command.type.InformationCommand;

public class Info implements NoArgumentCommand, InformationCommand {
private static Info info ;
public static Info getInstance(){
    if (info == null) {
    info = new Info();
    }
return info;
}
    private Info(){}
    @Override
    public String execute() {
        return controller.info();
    }

    @Override
    public String name() {
        return "info";
    }
}
