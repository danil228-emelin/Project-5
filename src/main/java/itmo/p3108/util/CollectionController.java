package itmo.p3108.util;

import itmo.p3108.model.Person;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;
import java.util.ArrayList;

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
        }
        return controller;
    }

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
}
