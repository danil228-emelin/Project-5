package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckFail {
    @Getter

    private static String path;

    private CheckFail() {
    }

    private static String readFileName() {
        String s = null;
        while (s == null) {
            System.out.println("Введите имя фаила  с расширеним  .xml для сохранения элементов");
            String test = UserReader.read();
            if (!test.matches("\\w+.xml")) {
                System.err.println("Ошибка:Строка имела неверный формат");
                continue;
            }
            s = test;
        }
        return s;

    }


    private static boolean fileCheck(String test) {
        if (!test.matches("\\w+\\.xml")) {
            System.err.println("Фаил имел неверный формат");
            return false;
        }
        Path path = Paths.get(test);
        if (Files.notExists(path)) {
            return true;
        }
        if (Files.isWritable(path) && Files.isReadable(path))
            return true;
        else {
            System.err.println("Нет прав для записи или чтения:" + path);
            return false;
        }

    }


    public static void execute() {
        path = System.getenv("COLLECTION_PATH");
        if (path == null) {
            System.err.println("Фаил для сохранения коллекции не указан");
            path = readFileName();
        } else {
            System.out.println("Фаил по умолчанию для хранения элементов " + path);
        }
        boolean isFileAlright = false;
        while (!isFileAlright) {

            isFileAlright = fileCheck(path);
            if (!isFileAlright) {
                path = readFileName();
            }
        }
        System.out.println("Фаил успешно задан");
    }

    public static class SetPath implements Command {
        private static SetPath setPath;
        private String testPath;

        public static SetPath getInstance() {
            if (setPath == null) {
                setPath = new SetPath();
            }
            return setPath;
        }

        public void setPath(String failPath) {
            testPath = failPath;
        }

        @Override
        public String execute() {
            if (fileCheck(testPath)) {
                path = testPath;
                return "Фаил " + testPath + " успешно установлен";
            }
            System.err.println("Фаил " + testPath + " не может быть использован");
            return "";

        }

        public String name() {
            return "set_path";
        }
    }
}
