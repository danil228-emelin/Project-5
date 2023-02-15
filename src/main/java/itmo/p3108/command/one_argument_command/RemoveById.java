package itmo.p3108.command.one_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;

/**
 * remove element with certain id
 */
public class RemoveById implements Command {
    private static RemoveById remove;
    private Long id;

    private RemoveById() {
    }

    public static RemoveById getInstance() {
        if (remove == null) {
            remove = new RemoveById();
        }
        return remove;
    }


    @Override
    public String execute() {
        controller.getPersonList().removeIf(x -> controller.isPersonExist(id));
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
            throw new ValidationException("error:id>0");
        }

        if (!controller.isPersonExist(id)) {
            throw new ValidationException("error:person wih such id doesn't exit");
        }
        this.id = id;
        return this;
    }
}
