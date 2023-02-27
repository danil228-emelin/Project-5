package itmo.p3108.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class FileWorker,class for reading and writing data from,to  the file
 */

public class FileWorker {
    private FileWorker() {
    }

    /**
     * @param path source fail
     * @return fail content
     * @throws IOException happen when  problems  with file occur
     */
    public static String read(String path) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (bufferedInputStream.available() > 0) {
                stringBuilder.append((char) bufferedInputStream.read());
            }
            return stringBuilder.toString();
        }
    }

    /**
     * @param path target file
     * @param line line to write
     * @throws IOException happen when  problems  with file occur
     */
    public static void write(String path, String... line) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path, true)) {
            fileWriter.write("\n");
            if (line.length == 0) {
                return;
            }
            for (String s : line) {
                fileWriter.write(s);
                fileWriter.write("\n");
            }
        }
    }


}
