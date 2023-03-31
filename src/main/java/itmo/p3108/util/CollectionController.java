package itmo.p3108.util;

import itmo.p3108.exception.ValidationException;
import itmo.p3108.model.Person;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * CollectionController class.Contain Collection of elements.
 * Contain the root element of xml file,it needs for creating certain xml format(tree format)
 */
@Slf4j
@XmlRootElement(name = "collection")
public final class CollectionController {
    private static CollectionController controller = new CollectionController();
    private final ArrayList<Person> personList;
    private final LocalDate localDate;

    private CollectionController() {
        this.personList = new ArrayList<>();
        this.localDate = LocalDate.now();
    }

    public static void setController(CollectionController controller) {
        CollectionController.controller = controller;
    }

    public static CollectionController getInstance() {
        return controller;
    }

    /**
     * return person list
     */
    @XmlElement(name = "person")
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    /**
     * @return provided main information about collection
     * @see itmo.p3108.command.Info
     */
    public String info() {
        return "Тип:ArrayList\n" + "Дата инициализации:" +
                localDate + "\n"
                + "Количество элементов:" + personList.size();
    }

    public boolean isEmpty() {
        return personList.isEmpty();
    }


    public Person getPersonById(Long id) {
        for (Person person : personList) {
            if (person.getPersonId().equals(id)) {
                return person;
            }
        }
        throw new ValidationException("error:person with such id doesn't  exist");
    }

    public boolean isPersonExist(Long id) {
        for (Person person : personList) {
            if (person.getPersonId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
