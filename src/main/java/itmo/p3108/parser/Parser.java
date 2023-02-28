package itmo.p3108.parser;

import itmo.p3108.command.type.Command;
import itmo.p3108.exception.FileException;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.model.*;
import itmo.p3108.util.CheckData;
import itmo.p3108.util.CollectionController;
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
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class Parse,parse elements from and to the xml file
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Parser {
    private static final ArrayList<String> ARGUMENTS = new ArrayList<>();


    /**
     * @param element certain node of xml file
     * @param tegName certain leave from node
     * @return information which leave has
     */
    private static String information(Element element, String tegName) {
        return element.getElementsByTagName(tegName).item(0).getTextContent();
    }

    /**
     * @param path read from file,treated it as xml file
     *             find  all nodes,if one of the attribute is null or missed-error raise
     */
    public static void read(@NonNull String path) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            long max_id = 0L;
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            File file = new File(path);
            if (file.length() == 0) {
                log.warn(String.format("Fail %s is empty, collection empty as well", path));
                throw new FileException("Fail " + path + " is empty, collection empty as well");
            }
            Document doc = db.parse(file);

            doc.getDocumentElement().normalize();

            NodeList personList = doc.getElementsByTagName("person");

            for (int temp = 0; temp < personList.getLength(); temp++) {
                Node personNode = personList.item(temp);

                if (personNode.getNodeType() == Node.ELEMENT_NODE) {


                    Element personElem = (Element) personNode;

                    String eyeColour = (information(personElem, "eyeColor"));

                    String id = (information(personElem, "id"));
                    ARGUMENTS.add(id);
                    String name = (information(personElem, "name"));
                    ARGUMENTS.add(name);
                    String height = (information(personElem, "height"));
                    ARGUMENTS.add(height);
                    String createDate = (information(personElem, "creationDate"));
                    ARGUMENTS.add(createDate);
                    String birthday = (information(personElem, "birthday"));
                    ARGUMENTS.add(birthday);
                    String nationality = (information(personElem, "nationality"));
                    ARGUMENTS.add(nationality);

                    String xCoordinates = "";
                    String yCoordinates = "";
                    NodeList coordinateList = doc.getElementsByTagName("coordinates");

                    String x = "";
                    String y = "";
                    String z = "";
                    String placeName = "";

                    for (int i = 0; i < coordinateList.getLength(); i++) {
                        Element coordinateElem = (Element) coordinateList.item(i);
                        xCoordinates = (information(coordinateElem, "x"));
                        yCoordinates = (information(coordinateElem, "y"));
                    }
                    NodeList location = doc.getElementsByTagName("location");

                    for (int i = 0; i < location.getLength(); i++) {
                        Element coordinateElem = (Element) location.item(i);
                        x = (information(coordinateElem, "x"));
                        y = (information(coordinateElem, "y"));
                        z = (information(coordinateElem, "z"));
                        placeName = (information(coordinateElem, "name"));
                    }

                    CheckData checkData = new CheckData();
                    if (!checkData.wrapperCheckArguments(ARGUMENTS)) {
                        log.error("Error during parsing:some attributes was in incorrect format,change or fix xml file");
                        System.err.println("Error during parsing:some attributes was in incorrect format,change or fix xml file");
                        continue;
                    }

                    Person person = Person
                            .builder()
                            .id(Long.parseLong(id))
                            .name(name)
                            .height(Double.parseDouble(height))
                            .eyeColor(Color.valueOf(eyeColour))
                            .creationDate(ZonedDateTime.parse(createDate))
                            .nationality(Country.valueOf(nationality))
                            .birthday(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MM-dd-yyyy")))
                            .coordinates(Coordinates.builder().x(Integer.parseInt(xCoordinates)).y(Float.valueOf(yCoordinates))
                                    .build()).location(Location.builder().name(placeName).x(Double.parseDouble(x))
                                    .y(Float.valueOf(y)).z(Float.parseFloat(z)).name(placeName).build()).build();

                    if (!Command.controller.isPersonExist(person.getId())) {
                        Command.controller.getPersonList().add(person);
                        max_id = Math.max(Long.parseLong(id), max_id);
                    }
                }


            }
            PersonReadingBuilder.setId(max_id);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error("Error during parsing:file has incorrect data,collection is empty,change or fix xml file");
            System.err.println("Error during parsing:file has incorrect data,collection is empty");
        } catch (ValidationException | FileException e) {
            log.error(e.getMessage());
            System.err.println(e.getMessage());
        } catch (NullPointerException | NoSuchElementException e) {
            log.error("Error during parsing:One of the element is null,change or fix xml file");
            System.err.println("Error during parsing:One of the element is null,change or fix xml file");
        }

    }

    /**
     * @param path source,treated as xml file
     * @throws JAXBException         occur when parser detects error in nodes format
     * @throws FileNotFoundException occur when file doesn't exist
     */
    public static void write(@NonNull String path) throws JAXBException, FileNotFoundException {

        CollectionController controller = CollectionController.getInstance();
        JAXBContext contextObj = JAXBContext.newInstance(CollectionController.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshallerObj.marshal(controller, new FileOutputStream(path));


    }

}
