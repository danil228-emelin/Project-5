package itmo.p3108.parser;

import itmo.p3108.util.CollectionController;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;

/**
 * Class Parse,parse elements from and to the xml file
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Parser {

    private static final String EMPTY_FAIL = "Fail is empty, collection empty as well";

    /**
     * read elements from fail and convert it to objects.
     *
     * @param path file to read
     */
    public static Optional<CollectionController> read(@NonNull String path) {

        try {
            File file = new File(path);
            if (file.length() == 0) {
                return Optional.empty();
            }
            JAXBContext contextObj = JAXBContext.newInstance(CollectionController.class);
            Unmarshaller ums = contextObj.createUnmarshaller();
            CollectionController controller = (CollectionController) ums.unmarshal(file);
            return Optional.of(controller);
        } catch (JAXBException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Write elements to file,serialize them
     *
     * @param path fail to write
     * @throws JAXBException         problem with parser
     * @throws FileNotFoundException IO exception.
     */
    public static void write(@NonNull String path) throws JAXBException, FileNotFoundException {

        CollectionController controller = CollectionController.getInstance();
        JAXBContext contextObj = JAXBContext.newInstance(CollectionController.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(controller, new FileOutputStream(path));


    }

}
