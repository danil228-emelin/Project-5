package itmo.p3108.command.one_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * remove element with certain id
 */
@Slf4j
public class RemoveById implements Command {
    private static RemoveById remove;
    private Long id;

    private RemoveById() {
    }

    public static RemoveById getInstance() {
        if (remove == null) {
            remove = new RemoveById();
            log.info("RemoveById initialized");
        }
        return remove;
    }


    @Override
    public String execute() {
        controller.getPersonList().removeIf(x -> controller.isPersonExist(id));
        log.info("RemoveById:element with  id "+id+" was deleted successfully");

        return "element with  id "+id+" was deleted successfully";
    }

    @Override
    public String name() {
        return "remove_by_id";
    }

    /**
     * set id
     */
    public RemoveById setId(Long id) {
        if (id <= 0) {
            log.error(" RemoveById error during setting id:id>0");
            throw new ValidationException("error:id>0");
        }

        if (!controller.isPersonExist(id)) {
            log.error(" RemoveById error :person with such "+id +"doesn't exit");

            throw new ValidationException("error:person with such id doesn't exit");
        }
        this.id = id;
        return this;
    }
}
