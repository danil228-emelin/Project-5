package itmo.p3108.parser;

import itmo.p3108.exception.FileException;
import itmo.p3108.model.Person;
import itmo.p3108.util.CheckData;
import itmo.p3108.util.CollectionController;
import itmo.p3108.util.Reflection;
import itmo.p3108.util.annotation.Checking;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

/**
 * Class Parse,parse elements from and to the xml file
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Parser {
    private static final ArrayList<String> ARGUMENTS = new ArrayList<>();
    /**
     * @param path read from file,treated it as xml file
     * find  all nodes,if one of the attribute is null or missed-error raise
     */
    private static final String EMPTY_FAIL = "Fail is empty, collection empty as well";
    private static final String PARSING_FAIL = "Error during parsing:some attributes was in incorrect format or missed";

    /**
     * @param element certain node of xml file
     * @param tegName certain leave from node
     * @return information which leave has
     */
    private static String information(Element element, String tegName) {
        return element.getElementsByTagName(tegName).item(0).getTextContent();
    }

    public static void read(@NonNull String path) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            long max_id = 0L;
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            File file = new File(path);
            if (file.length() == 0) {
                log.warn(EMPTY_FAIL);
                throw new FileException(EMPTY_FAIL);
            }
            Document doc = db.parse(file);

            doc.getDocumentElement().normalize();

            NodeList personList = doc.getElementsByTagName("person");
            CheckData checkData = new CheckData();
            Set<Method> validationMethods = Reflection.findAllClassesWithAnnotation("itmo.p3108.util", Checking.class);
            Person.PersonBuilder personBuilder = Person.builder();
            for (int i = 0; i < personList.getLength(); i++) {
                NodeList personAttributes = personList.item(i).getChildNodes();
                for (int z = 0; z < personAttributes.getLength(); z++) {
                    Node attribute = personAttributes.item(z);
                    if (attribute.getNodeName().startsWith("#") || attribute.getChildNodes().getLength() > 1) {
                        continue;
                    }
                    boolean result = false;
                    for (Method method : validationMethods) {
                        if (method.getName().toLowerCase().contains(attribute.getNodeName().toLowerCase())) {
                            result = (boolean) method.invoke(checkData, attribute.getTextContent());
                        }
                    }
                    if (!result) {
                        System.err.printf("Error during parsing,Element %d  has incorrect %s\n", i, attribute.getNodeName());
                        continue;
                    }
                    Method method = Reflection.findMethodInClass(Person.PersonBuilder.class, attribute.getNodeName());
                    if (method == null) {
                        log.error(String.format("Method for attribute %s in Person builder wasn't found", attribute.getNodeName()));
                        continue;
                    }
                    method.invoke(personBuilder, attribute.getNodeName());
                }
                Person person = personBuilder.build();
                System.out.println(person);
            }

        } catch (IOException | SAXException | ParserConfigurationException e) {
            log.error(e.getMessage());
            System.err.println("Error during parsing.Fail has incorrect data");
        } catch (InvocationTargetException | IllegalAccessException error) {
            System.err.println("Error during parsing.Checking methods are incorrect");
        }
    }

    public static void write(@NonNull String path) throws JAXBException, FileNotFoundException {

        CollectionController controller = CollectionController.getInstance();
        JAXBContext contextObj = JAXBContext.newInstance(CollectionController.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(controller, new FileOutputStream(path));


    }

}
