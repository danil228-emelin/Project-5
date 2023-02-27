package itmo.p3108.command.type;

import itmo.p3108.util.CollectionController;

/**
 * common interface for all commands,
 * if command implement only this interface,it is implied that it has  one argument
 */
public interface Command {
    CollectionController controller = CollectionController.getInstance();

    String execute();

    String description();

    String name();
}
