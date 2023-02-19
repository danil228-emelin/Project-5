package itmo.p3108.command.one_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * count elements with certain height
 */
@Slf4j
public class CountByHeight implements Command {
    private static CountByHeight count;
    private double height;

    public static CountByHeight getInstance() {
        if (count == null) {
            count = new CountByHeight();
            log.info("CountBtHeight initialized");
        }
        return count;
    }

    @Override
    public String execute() {
        log.info("CountBtHeight printing");

        return Long.toString(controller.getPersonList().stream().parallel().filter(x -> x.getHeight() == height).count());
    }

    @Override
    public String description() {
        return
                "count_by_height height:посчитать количество элементов с заданным возростом";

    }

    /**
     * set height,call before execute method
     *
     */
    public CountByHeight setHeight(double height) {
        if (height <= 0) {
            log.error("CountBtHeight error during setting height:height must be more than 0");

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
