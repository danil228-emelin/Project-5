package itmo.p3108;

import itmo.p3108.model.Person;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

public final class CollectionController {
    private static CollectionController controller;
    private ArrayList<Person> personList;
    private LocalDate localDate;

    private CollectionController() {
        personList = new ArrayList<>();
        localDate = LocalDate.now();

    }

    public static CollectionController getInstance() {
        if (controller == null) {
            controller = new CollectionController();
        }
        return controller;
    }

    public String info() {
        return "Тип:ArrayList\n" + "Дата инициализации:\n"
                + "Количество элементов:" + personList.size();
    }
public boolean isEmpty(){
     return personList.isEmpty();
}
    public ArrayList<Person> getPersonList() {
        return personList;
    }
}
