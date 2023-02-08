package itmo.p3108.command;

import itmo.p3108.CollectionController;

public interface Command {
    CollectionController controller = CollectionController.getInstance() ;
    String execute();

    String name();
}
