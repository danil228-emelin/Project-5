package itmo.p3108.command;

import itmo.p3108.command.type.IndependentCommand;
import itmo.p3108.command.type.NoArgumentCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Command Info, put out major information of collection
 * Size,Collection Type,Initialization Date
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SuppressWarnings("unused")
public class Info implements NoArgumentCommand, IndependentCommand {
    @Override

    /**
     * put out major information of collection
     */
    public String execute() {


        return controller.info();
    }

    @Override
    public String description() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }


    @Override
    public String name() {
        return "info";
    }
}
