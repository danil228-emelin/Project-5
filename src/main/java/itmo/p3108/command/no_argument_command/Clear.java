package itmo.p3108.command.no_argument_command;

import itmo.p3108.command.type.NoArgumentCommand;

/**
 *clear collection
 */
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

    /**
     * call clear function
     */
    @Override

    public String execute() {
        controller.getPersonList().clear();
        return "elements are deleted successfully";
    }

    @Override
    public String name() {
        return "clear";
    }
}
