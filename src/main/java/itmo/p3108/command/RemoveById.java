package itmo.p3108.command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Command RemoveByID,remove element with certain id,
 * has int number as parameter
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RemoveById implements Command {
    private Long id;

    @Override
    public String execute() {
        controller.getPersonList().removeIf(x -> x.getPersonId().equals(id));

        return "element with  id " + id + " was deleted ";
    }

    @Override
    public String description() {
        return "remove_by_id id:удалить из коллекции элемент с заданным id";
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
            log.error(String.format(" RemoveById error :element with such %d doesn't exit", id));

            throw new ValidationException("RemoveById error:person with such id doesn't exit");
        }
        this.id = id;
        return this;
    }
}
