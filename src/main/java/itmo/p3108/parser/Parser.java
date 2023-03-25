package itmo.p3108.parser;

import com.sun.istack.Nullable;
import itmo.p3108.exception.FileException;
import itmo.p3108.model.Person;
import itmo.p3108.util.CheckData;
import itmo.p3108.util.CollectionController;
import itmo.p3108.util.Reflection;
import itmo.p3108.util.annotation.BuilderClass;
import itmo.p3108.util.annotation.Checking;
import itmo.p3108.util.annotation.ParsingMethod;
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
import java.util.List;
import java.util.Set;

/**
 * Class Parse,parse elements from and to the xml file
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Parser {
    /**
     * @param path read from file,treated it as xml file
     * find  all nodes,if one of the attribute is null or missed-error raise
     */
    private static final String EMPTY_FAIL = "Fail is empty, collection empty as well";
    private static final String PARSING_FAIL = "Error during parsing:some attributes was in incorrect format or missed";
    private static final int AMOUNT_OF_ATTRIBUTES = 19;
    private static final Set<Method> validationMethods = Reflection.findAllMethodsWithAnnotation("itmo.p3108.util", Checking.class);

    /**
     * @param element certain node of xml file
     * @param tegName certain leave from node
     * @return information which leave has
     */
    private static String information(Element element, String tegName) {
        return element.getElementsByTagName(tegName).item(0).getTextContent();
    }

    private static Document createDocument(String path) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            File file = new File(path);
            if (file.length() == 0) {
                log.warn(EMPTY_FAIL);
                throw new FileException(EMPTY_FAIL);
            }
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (IOException | SAXException | ParserConfigurationException e) {
            log.error(e.getMessage());
            System.err.println("Error during parsing.Fail has incorrect data");
            return null;
        }
    }

    @Nullable
    private static boolean checkAttribute(Node attribute) {
        if (attribute == null) {
            System.err.println("Error during checking Attribute was null");
            return false;
        }
        CheckData checkData = new CheckData();

        assert validationMethods != null;
        Method validationMethod = null;
        for (Method method : validationMethods) {
            if (method.getName().toLowerCase().contains(attribute.getNodeName().toLowerCase())) {
                validationMethod = method;
                break;
            }
        }
        try {
            assert validationMethod != null;

            return (boolean) validationMethod.invoke(checkData, attribute.getTextContent());
        } catch (IllegalAccessException | InvocationTargetException | NullPointerException e) {
            log.error(e.getMessage());
            System.err.println("Error during parsing,validation methods are incorrect");
            return false;
        }
    }

    private static List<Object> convertToBuilders() {
        List<Object> builders = new ArrayList<>();
        Set<Class<?>> builderOwners = Reflection.findAllAnnotatedClasses("itmo.p3108.model", BuilderClass.class);

        assert builderOwners != null;
        for (Class<?> builderOwner : builderOwners) {
            try {
                builders.add(builderOwner.getMethod("builder").invoke(null));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                log.error(e.getMessage());
            }
        }
        return builders;
    }

    @Nullable
    private static Boolean FillBuilder(Object builder, Node attribute) {
        Method[] methods = builder.getClass().getMethods();
        Method fillMethod = null;
        for (Method method : methods) {
            if (!method.isAnnotationPresent(ParsingMethod.class)) {
                continue;
            }
            if (method.getName().toLowerCase().contains(attribute.getNodeName().toLowerCase())) {
                fillMethod = method;
                break;
            }
        }
        if (fillMethod == null) {
            return false;
        }
        try {
            fillMethod.invoke(builder, attribute.getTextContent());
            return true;
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public static void read(@NonNull String path) {
        long max_id = 0L;
        Document doc = createDocument(path);
        List<Object> builders = convertToBuilders();

        assert doc != null;
        NodeList personList = doc.getElementsByTagName("person");

        for (int person = 0; person < personList.getLength(); person++) {
            NodeList personAttributes = personList.item(person).getChildNodes();
            if (personAttributes.getLength() < AMOUNT_OF_ATTRIBUTES) {
                System.err.printf("%s,element %d", PARSING_FAIL, person);
                continue;
            }
            for (int attributeCounter = 0; attributeCounter < personAttributes.getLength(); attributeCounter++) {
                Node attribute = personAttributes.item(attributeCounter);
                if (attribute.getNodeName().startsWith("#")) {
                    continue;
                }
                if (attribute.getChildNodes().getLength() > 1) {
                    continue;
                }
                boolean validationResult = checkAttribute(attribute);
                if (!validationResult) {
                    log.error(PARSING_FAIL);
                    return;
                }
                boolean result = false;
                for (Object builder : builders) {
                    result = FillBuilder(builder, attribute);
                    if (result) {
                        break;
                    }
                }
                if (!result) {
                    System.err.println("Builder has incorrect methods ");
                    System.err.println(attribute.getNodeName());
                    return;
                }

            }
            for (Object builder : builders) {
                if (builder instanceof Person.PersonBuilder) {
                    System.out.println(((Person.PersonBuilder) builder).build());
                }
            }
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
