package itmo.p3108.command.one_argument_command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.util.AnalyzerExecuteScript;
import itmo.p3108.util.FileWorker;
import itmo.p3108.util.Invoker;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * execute scripts
 */
@Slf4j
public class ExecuteScript implements Command {
    private static ExecuteScript executeScript;

    private String path;

    private ExecuteScript() {
    }

    public static ExecuteScript getInstance() {
        if (executeScript == null) {
            executeScript = new ExecuteScript();
            log.info("ExecuteScript initialized");
        }
        return executeScript;
    }

    /**
     * set path ,call before execute method
     */
    public void setPath(String path) {
        Path test = Path.of(path);
        if (!Files.isReadable(test) || !Files.isWritable(test)) {
            log.info("ExecuteScript error during setting path:can't read and write  file");

            throw new ValidationException("error:can't read and write file");
        }
        this.path = path;
    }

    @Override
    public String execute() {
        Invoker invoker = Invoker.getInstance();

        try {
            String[] commands = FileWorker.read(path).split("\n");

            AnalyzerExecuteScript.analyze(commands);
            log.info("Script " + path + " executed ");
            return "ExecuteScript script " + path + " executed ";
        } catch (IOException e) {
            log.error("Execute Script:fail error ");
            System.err.println("Execute script:file error");
            return "";
        }
    }

    @Override
    public String description() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    @Override
    public String name() {
        return "execute_script";
    }


}
