package itmo.p3108.parser;

import com.sun.istack.Nullable;
import itmo.p3108.command.type.Command;
import itmo.p3108.exception.FileException;
import itmo.p3108.model.Person;
import itmo.p3108.model.PersonReadingBuilder;
import itmo.p3108.util.Builder;
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
import java.util.*;

/**
 * Class Parse,parse elements from and to the xml file
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Parser {

    private static final String EMPTY_FAIL = "Fail is empty, collection empty as well";
    private static final int AMOUNT_OF_ATTRIBUTES = 19;
    private static final Optional<Set<Class<?>>> builderOwners = Reflection.findAllAnnotatedClasses("itmo.p3108.model", BuilderClass.class);


    /**
     * @param path to fail
     * @return document from which parser will read
     */
    private static Optional<Document> createDocument(String path) {
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
            return Optional.of(doc);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            log.error(e.getMessage());
            System.err.printf("createDocument.%s is incorrect\n", path);
            return Optional.empty();
        } catch (FileException e) {
            log.error(e.getMessage());
            System.err.println(EMPTY_FAIL);
            return Optional.empty();
        }
    }

    /**
     * Check whether the attribute has proper value.
     *
     * @param attributes of person.For instance,name,height.
     * @return result of checking.
     */
    @Nullable
    private static boolean checkAttributes(List<Node> attributes, Set<Method> validationMethods) {

        CheckData checkData = new CheckData();

        for (Node personAttribute : attributes) {
            Method validationMethod = null;
            for (Method method : validationMethods) {
                if (method.getName().toLowerCase().contains(personAttribute.getNodeName().toLowerCase())) {
                    validationMethod = method;
                    break;
                }
            }

            try {
                if (validationMethod == null) {
                    System.err.println("Error during checkAttributes,validation method is null");
                    return false;
                }
                boolean validationResult = (boolean) validationMethod.invoke(checkData, personAttribute.getTextContent());
                if (!validationResult) {
                    System.err.println(personAttribute.getNodeName() + " has incorrect data");
                    return false;
                }
            } catch (IllegalAccessException | InvocationTargetException | NullPointerException e) {
                log.error(e.getMessage());
                System.err.printf("checkAttributes,validation methods are incorrect.\nMethod-%s,attribute-%s", validationMethod, personAttribute.getNodeName());
                return false;
            }
        }
        return true;
    }

    /**
     * Create builders of certain classes.
     * All classes are taken with a help of reflection.
     * These classes are  marked with annotation BuilderClass.
     *
     * @return list of builders.
     */
    private static Optional<List<Object>> convertToBuilders() {
        List<Object> builders = new ArrayList<>();


        if (builderOwners.isEmpty()) {
            System.err.println("convertToBuilders.Classes which have builders are null");
            return Optional.empty();
        }
        for (Class<?> builderOwner : builderOwners.get()) {
            try {
                builders.add(builderOwner.getMethod("builder").invoke(null));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                log.error(e.getMessage());
                System.err.println("convertToBuilders,builder method was incorrect Class-" + builderOwner.getSimpleName());
                return Optional.empty();
            }
        }
        return Optional.of(builders);
    }


    @Nullable
    private static Boolean fillBuilderWithDifficultObjects(Person.PersonBuilder targetBuilder, Object objectBuilder) {
        if (!(objectBuilder instanceof Builder)) {
            System.err.printf("%s doesn't implement Builder interface\n", objectBuilder);
            return false;
        }
        Method[] fillMethods = targetBuilder.getClass().getMethods();
        if (builderOwners.isEmpty()) {
            System.err.println("There is no classes with buildes");
            return false;
        }
        for (Class<?> builderOwner : builderOwners.get()) {
            if (objectBuilder.toString().toLowerCase().contains(builderOwner.getSimpleName().toLowerCase())) {
                Method properFillMethod = null;
                for (Method method : fillMethods) {
                    if (!(method.getParameterCount() == 1)) {
                        continue;
                    }
                    if (objectBuilder.getClass().getSimpleName().toLowerCase().contains(method.getName().toLowerCase())) {
                        properFillMethod = method;
                        break;
                    }
                }
                if (properFillMethod == null) {
                    System.err.println("There is set method in Person Builder for " + objectBuilder.getClass().getSimpleName());
                    return false;
                }
                try {
                    properFillMethod.invoke(targetBuilder, ((Builder) objectBuilder).build());
                    return true;
                } catch (IllegalAccessException | InvocationTargetException | AssertionError e) {
                    log.error(e.getMessage());
                    System.err.println("fillBuilderWithDifficultObjects,methods are incorrect");
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * set certain field  of Builder.
     *
     * @param builder    in which try to set data
     * @param attributes want to set in builder.
     * @return true-if set.
     */
    @Nullable
    private static Boolean fillBuilder(Object builder, List<Node> attributes) {
        Method[] methods = builder.getClass().getMethods();
        for (Node personAttribute : attributes) {
            Method fillMethod = null;
            for (Method method : methods) {
                if (!method.isAnnotationPresent(ParsingMethod.class)) {
                    continue;
                }
                if (method.getName().toLowerCase().contains(personAttribute.getNodeName().toLowerCase())) {
                    fillMethod = method;
                    break;
                }
            }
            if (fillMethod == null) {
                continue;
            }
            try {
                fillMethod.invoke(builder, personAttribute.getTextContent());
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error(e.getMessage());
                System.err.println("FillBuilder. methods are incorrect.");
                return false;
            }

        }
        return true;
    }

    private static void findAllNodes(Node personNode, List<Node> listAttributes) {
        class FindAllNodes {
            void find(Node node) {
                if (node.getChildNodes().getLength() == 1) {
                    listAttributes.add(node);
                    return;
                }
                NodeList nodeList = node.getChildNodes();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node currentNode = nodeList.item(i);
                    if (currentNode.getNodeName().startsWith("#")) {
                        continue;
                    }

                    Element element = (Element) currentNode;
                    find(element);
                }

            }
        }
        FindAllNodes findAllNodes = new FindAllNodes();
        NodeList personAttributes = personNode.getChildNodes();
        for (int attributeCounter = 0; attributeCounter < personAttributes.getLength(); attributeCounter++) {
            Node attribute = personAttributes.item(attributeCounter);
            if (attribute.getNodeName().startsWith("#")) {
                continue;
            }
            findAllNodes.find(attribute);
        }
    }

    private static Optional<Person> createPerson(List<Object> builders) {
        Person.PersonBuilder personBuilder = null;
        Iterator<Object> objectIterator = builders.listIterator();
        while (objectIterator.hasNext()) {
            Object builder = objectIterator.next();
            if (builder instanceof Person.PersonBuilder) {
                personBuilder = (Person.PersonBuilder) builder;
                break;
            }
        }
        if (personBuilder == null) {
            System.err.println("Person Builder is missed");
            return Optional.empty();
        }
        objectIterator = builders.listIterator();
        while (objectIterator.hasNext()) {

            Object builder = objectIterator.next();
            if (builder instanceof Person.PersonBuilder) {
                continue;
            }
            if (!fillBuilderWithDifficultObjects(personBuilder, builder)) {
                System.err.println("fillDifficultObjects failed for " + builder.getClass().getSimpleName());
                return Optional.empty();
            }
        }
        return Optional.of(personBuilder.build());
    }

    /**
     * read elements from fail and convert it to objects.
     *
     * @param path file to read
     */
    public static void read(@NonNull String path) {
        long max_id = 0L;
        final Optional<Document> doc = createDocument(path);
        if (doc.isEmpty()) {
            return;
        }
        NodeList personList = doc.get().getElementsByTagName("person");
        final Optional<List<Object>> builders = convertToBuilders();
        if (builders.isEmpty()) {
            return;
        }
        final Optional<Set<Method>> validationMethods = Reflection.findAllMethodsWithAnnotation("itmo.p3108.util", Checking.class);
        if (validationMethods.isEmpty()) {
            System.err.println("Error during parsing.validation methods are null");
            return;
        }

        for (int person = 0; person < personList.getLength(); person++) {
            List<Node> attributes = new ArrayList<>(AMOUNT_OF_ATTRIBUTES);

            findAllNodes(personList.item(person), attributes);

            boolean validationResult = checkAttributes(attributes, validationMethods.get());
            if (!validationResult) {
                System.err.println("checkAttributes failed");
                continue;
            }
            var builderInsertResult = false;
            for (Object builder : builders.get()) {
                builderInsertResult = fillBuilder(builder, attributes);

                if (!builderInsertResult) {
                    System.err.printf("Error during filling " + builder.getClass().getSimpleName());
                }
            }
            if (!builderInsertResult) {
                return;
            }
            Optional<Person> optionalPerson = createPerson(builders.get());
            if (optionalPerson.isEmpty()) {
                System.err.println("Error person wasn't created");
                continue;
            }
            Command.controller.getPersonList().add(optionalPerson.get());
            max_id = Math.max(optionalPerson.get().getPersonId(), max_id);
        }
        PersonReadingBuilder.setId(max_id);

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
