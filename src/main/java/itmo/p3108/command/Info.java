package itmo.p3108.command;

public class Info implements NoArgumentCommand, Informationable {
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
