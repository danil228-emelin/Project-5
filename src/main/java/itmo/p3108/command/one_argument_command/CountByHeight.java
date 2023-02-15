package itmo.p3108.command.one_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;

/**
 * count elements with certain height
 */
public class CountByHeight implements Command {
    private static CountByHeight count;
    private double height;

    public static CountByHeight getInstance() {
        if (count == null) {
            count = new CountByHeight();
        }
        return count;
    }

    @Override
    public String execute() {
        return Long.toString(controller.getPersonList().stream().filter(x -> x.getHeight() == height).count());
    }

    /**
     * set height,call before execute method
     *
     */
    public CountByHeight setHeight(double height) {
        if (height <= 0) {
            throw new ValidationException("error:height must be more than 0");
        }

        this.height = height;
        return this;
    }

    @Override
    public String name() {
        return "count_by_height";
    }
}
