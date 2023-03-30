package itmo.p3108.command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Command CountByHeight,count elements with certain height,
 * don't work if collection is empty.
 * Has one parameter height
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CountByHeight implements Command {
    private static final String VALIDATION_ERROR = "CountBtHeight error during setting height:height must be more than 0";
    private double height;

    /**
     * @return number of elements,who has certain hight
     */
    @Override
    public String execute() {

        return Long.toString(controller.getPersonList().stream().parallel().filter(x -> x.getPersonHeight() == height).count());
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
            log.error(VALIDATION_ERROR);

            throw new ValidationException(VALIDATION_ERROR);
        }

        this.height = height;
        return this;
    }
}
