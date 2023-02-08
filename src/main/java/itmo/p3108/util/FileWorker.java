package itmo.p3108.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileWorker {
    private FileWorker() {
    }

    public static String read(String path) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (bufferedInputStream.available() > 0) {
                stringBuilder.append((char) bufferedInputStream.read());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void write(String path, String... line) {
        try (FileWriter fileWriter = new FileWriter(path, true)) {
            fileWriter.write("\n");
            for (String s : line) {
                fileWriter.write(s);
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
