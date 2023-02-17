package itmo.p3108.util;

import itmo.p3108.exception.ValidationException;
import itmo.p3108.model.Person;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * contain array list
 */
@Slf4j
@XmlRootElement(name = "collection")
public final class CollectionController {
    private static CollectionController controller;
    private ArrayList<Person> personList;
    private LocalDate localDate;

    private CollectionController() {
        this.personList = new ArrayList<>();
        this.localDate = LocalDate.now();
    }

    public static CollectionController getInstance() {
        if (controller == null) {
            controller = new CollectionController();
            log.info("CollectionController initialized");
        }
        return controller;
    }

    /**
     *
     *return person list
     */
    @XmlElement(name = "person")
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public String info() {
        return "Тип:ArrayList\n" + "Дата инициализации:" +
                localDate + "\n"
                + "Количество элементов:" + personList.size();
    }

    public boolean isEmpty() {
        return personList.isEmpty();
    }


    public void add(Person person) {
        if (!personList.contains(person)) {
            personList.add(person);
        }
    }

    public Person getPersonById(Long id) {
        for (Person person : personList) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        throw new ValidationException("error:person with such id doesn't  exist");
    }

    public boolean isPersonExist(Long id) {
        for (Person person : personList) {
            if (person.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
