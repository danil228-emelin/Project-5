package itmo.p3108.command.type;

import itmo.p3108.util.CollectionController;

public interface Command {
    CollectionController controller = CollectionController.getInstance() ;
    String execute();

    String name();
}
