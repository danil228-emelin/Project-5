package itmo.p3108.command.one_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.util.FileWorker;
import itmo.p3108.util.Invoker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * execute scripts
 */
public class ExecuteScript implements Command {
    private static ExecuteScript executeScript;

    private String path;

    private ExecuteScript() {
    }

    public static ExecuteScript getInstance() {
        if (executeScript == null) {
            executeScript = new ExecuteScript();

        }
        return executeScript;
    }
    /**
     * set path ,call before execute method
     *
     */
    public void setPath(String path) {
        Path test = Path.of(path);
        if (!Files.isReadable(test)) {
            throw new ValidationException("error:can't read from frile");
        }
        this.path = path;
    }

    @Override
    public String execute() {
        Invoker invoker = Invoker.getInstance();

        try {
            String[] commands = FileWorker.read(path).split("\n");
            for (String command : commands) {
                invoker.invoke(command);
            }
            return "Script " + path + " executed successfully";
        } catch (IOException e) {
            System.err.println("file error");
            return "Script doesn't execute ";
        }
    }

    @Override
    public String name() {
        return "execute_script";
    }


}
