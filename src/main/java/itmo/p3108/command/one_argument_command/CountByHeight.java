package itmo.p3108.command.one_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * count elements with certain height
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CountByHeight implements Command {
    private double height;


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

    @Override
    public String name() {
        return "count_by_height";
    }

    /**
     * set height,call before execute method
     */
    public CountByHeight setHeight(double height) {
        if (height <= 0) {
            log.error("CountBtHeight error during setting height:height must be more than 0");

            throw new ValidationException("error:height must be more than 0");
        }

        this.height = height;
        return this;
    }
}
