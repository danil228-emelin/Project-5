package itmo.p3108.command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;

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
        return "Элемент c id "+id+" удален";
    }

    @Override
    public String name() {
        return "remove_by_id";
    }

    public RemoveById setId(Long id) {
        if (!controller.isPersonExist(id)) {
            throw new ValidationException("Ошибка:Человека с таким id не существует");
        }
        this.id = id;
        return this;
    }
}
