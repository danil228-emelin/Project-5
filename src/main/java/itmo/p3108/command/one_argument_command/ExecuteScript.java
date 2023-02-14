package itmo.p3108.command.one_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.util.FileWorker;
import itmo.p3108.util.Invoker;
import lombok.Setter;

public class ExecuteScript implements Command {
    private static ExecuteScript executeScript;
    @Setter
    private String path;
    private ExecuteScript() {
    }

    public static ExecuteScript getInstance() {
        if (executeScript == null) {
            executeScript = new ExecuteScript();

        }
        return executeScript;
    }

    @Override
    public String execute() {
        Invoker invoker = Invoker.getInstance();
        String[] commands = FileWorker.read(path).split("\n");
        for (String command:commands){
            invoker.invoke(command);
            System.out.println();
        }

        return "Скрипт выполнен";
    }

    @Override
    public String name() {
        return "execute_script";
    }



}
