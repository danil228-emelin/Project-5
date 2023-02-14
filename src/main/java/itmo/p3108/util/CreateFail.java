package itmo.p3108.util;

import lombok.Getter;
import lombok.Setter;

public class CreateFail {
    @Getter
    @Setter
    private static String path = System.getenv("COLLECTION_PATH");

    private CreateFail() {
    }


    public static void execute() {

        System.err.println("Переменная окружения COLLECTION_PATH не указана");
        System.out.println(" Введите имя файла с расширеним .xml для работы с коллекцией или оставьте фаил по умолчанию(enter: " + path + ")");
        String test = UserReader.read();
        if (test.equals("")) {
            System.out.println("Переменная окружения не изменена");
            return;
        }
        String oldPath = path;
        while (path.equals(oldPath)) {
            System.out.println("Введите имя фаила  с расширеним .xml");
            test = UserReader.read();
            if (!test.matches("\\w+.xml")) {
                System.err.println("Ошибка:Строка имела неверный формат");
                continue;
            }
            path = test;

        }
    }

}
