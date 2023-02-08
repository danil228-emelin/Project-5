package itmo.p3108.command;

public class Info implements NoArgumentCommand {

    @Override
    public String execute() {
        return controller.info();
    }

    @Override
    public String name() {
        return "info";
    }
}
