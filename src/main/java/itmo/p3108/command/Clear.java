package itmo.p3108.command;

public class Clear implements NoArgumentCommand {
    private static Clear clear;

    private Clear() {
    }

    public static Clear getInstance() {

        if (clear == null) {
            clear = new Clear();
        }
        return clear;
    }

    @Override
    public String execute() {
        controller.getPersonList().clear();
        return "Элеменыт коллекции удалены";
    }

    @Override
    public String name() {
        return "clear";
    }
}
