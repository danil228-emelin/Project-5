package itmo.p3108.util;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CheckFail {
    @Getter
    @Setter
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


    private static boolean fileCheck() {
        if (Files.notExists(Paths.get(path))) {
            return true;
        }
        if (Files.isWritable(Paths.get(path)) || Files.isReadable(Paths.get(path)))
            return true;
        else {
            System.err.println("Нет прав для записи или чтения:" + path);
            return false;
        }

    }

    public static void execute() {
        path = System.getenv("COLLECTION_PATH");
        path = (path == null) ? System.getProperty("COLLECTION_PATH") : path;
        if (path == null) {
            System.err.println("Переменная окружения COLLECTION_PATH не указана");
            path = readFileName();
        }
        boolean isFileAlright = false;
        while (!isFileAlright) {

            isFileAlright = fileCheck();
            if (!isFileAlright) {
                path = readFileName();
            } else {
                System.setProperty("COLLECTION_PATH", path);
                System.out.println("Переменная окружения задана:" + path);
            }
        }
    }
//set переменную окружения.
}
