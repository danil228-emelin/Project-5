package itmo.p3108.parser;

import itmo.p3108.command.type.Command;
import itmo.p3108.model.*;
import itmo.p3108.util.CollectionController;
import lombok.NonNull;
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

public final class Parser {
    private Parser() {
    }

    private static String information(Element element, String tegName) {
        return element.getElementsByTagName(tegName).item(0).getTextContent();
    }

    public static void read(@NonNull String path) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            Long max_id = 0L;
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            File file = new File(path);

            if (file.length() == 0) {
                return;
            }
            Document doc = db.parse(file);

            doc.getDocumentElement().normalize();

            NodeList personList = doc.getElementsByTagName("person");
            for (int temp = 0; temp < personList.getLength(); temp++) {
                Node personNode = personList.item(temp);
                if (personNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element personElem = (Element) personNode;
                    String eyeColour = information(personElem, "eyeColor");
                    String id = information(personElem, "id");
                    String name = information(personElem, "name");
                    String height = information(personElem, "height");
                    String createDate = information(personElem, "creationDate");
                    String birthday = information(personElem, "birthday");
                    String nationality = information(personElem, "nationality");
                    String xCoordinates = "";
                    String yCoordinates = "";
                    NodeList coordinateList = doc.getElementsByTagName("coordinates");

                    for (int i = 0; i < coordinateList.getLength(); i++) {
                        Element coordinateElem = (Element) coordinateList.item(i);
                        xCoordinates = coordinateElem.getElementsByTagName("x").item(0).getTextContent();
                        yCoordinates = coordinateElem.getElementsByTagName("y").item(0).getTextContent();
                    }
                    NodeList location = doc.getElementsByTagName("location");
                    String x = "";
                    String y = "";
                    String z = "";
                    String placeName = "";

                    for (int i = 0; i < location.getLength(); i++) {
                        Element coordinateElem = (Element) location.item(i);
                        x = coordinateElem.getElementsByTagName("x").item(0).getTextContent();
                        y = coordinateElem.getElementsByTagName("y").item(0).getTextContent();
                        z = coordinateElem.getElementsByTagName("z").item(0).getTextContent();
                        placeName = coordinateElem.getElementsByTagName("name").item(0).getTextContent();
                    }

                    Person person = Person.builder()
                            .id(Long.parseLong(id))
                            .name(name)
                            .height(Double.parseDouble(height))
                            .eyeColor(Color.valueOf(eyeColour))
                            .creationDate(ZonedDateTime.parse(createDate))
                            .nationality(Country.valueOf(nationality))
                            .birthday(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MM-dd-yyyy")))
                            .coordinates(Coordinates.builder()
                                    .x(Integer.parseInt(xCoordinates))
                                    .y(Float.valueOf(yCoordinates))
                                    .build())
                            .location(Location.builder()
                                    .name(placeName)
                                    .x(Double.parseDouble(x))
                                    .y(Float.valueOf(y))
                                    .z(Float.parseFloat(z))
                                    .name(placeName)
                                    .build())
                            .build();


                    Command.controller.add(person);
                    max_id = Long.parseLong(id) > max_id ? Long.parseLong(id) : max_id;

                }


            }
            PersonReadingBuilder.setId(max_id);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void write(@NonNull String path) {

        try {
            CollectionController controller = CollectionController.getInstance();
            JAXBContext contextObj = JAXBContext.newInstance(CollectionController.class);

            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshallerObj.marshal(controller, new FileOutputStream(path));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

    }

}
