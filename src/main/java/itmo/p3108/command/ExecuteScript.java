package itmo.p3108.command;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.FileException;
import itmo.p3108.util.AnalyzerExecuteScript;
import itmo.p3108.util.FileWorker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Command execute script.
 * Execute script. Has one argument (path) for script.
 * If fail doesn't exist or program can't read from -error occur
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ExecuteScript implements Command {
    private static final String ERROR_PERMISSION = "ExecuteScript error during setting path:can't read and write  file";
    private static final String IOEXCEPTION = "Execute Script:fail error ";
    private String path;

    /**
     * set path ,call before execute method
     */
    public void setPath(String path) {
        Path test = Path.of(path);
        if (!Files.isReadable(test) || !Files.isWritable(test)) {

            throw new FileException(ERROR_PERMISSION);
        }
        this.path = path;
    }

    /**
     * @return the result of command
     */
    @Override
    public String execute() {
        String current_fail = path;
        try {
            String[] commands = FileWorker.read(path).split("\n");

            AnalyzerExecuteScript.analyze(commands);
            return String.format("Script %s executed ", current_fail);
        } catch (IOException e) {
            log.error(IOEXCEPTION);
            System.err.println(IOEXCEPTION);
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
