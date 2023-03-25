package itmo.p3108.model;

import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.util.CheckData;
import itmo.p3108.util.UserReader;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class PersonReadingBuilder created to build all @see {@link Person} attributes using console input
 */
public final class PersonReadingBuilder {
    private static PersonReadingBuilder createPerson;
    private static Long id = 1L;
    private final CheckData checkData = new CheckData();

    private PersonReadingBuilder() {
    }

    public static void setId(Long id1) {
        if (id1 > 0) {
            id = id1 + 1;
        }
    }

    public static PersonReadingBuilder getInstance() {
        if (createPerson == null) {
            createPerson = new PersonReadingBuilder();
        }
        return createPerson;
    }

    public String properRead(String message) {
        return Objects.requireNonNull(UserReader.read(), message).trim();
    }

    public String createName() {
        String name = "";
        while (name.equals("")) {
            System.out.println("enter name");
            String test = properRead("name must not be null");
            if (!checkData.checkName(test)) {
                continue;
            }
            name = test;
        }
        return name;
    }

    public Long createId() {
        return id++;
    }

    public double createHight() {

        double height = -1;
        while (height < 0) {
            String test = "";

            System.out.println("enter height");
            test = properRead("height must not be null");

            if (!checkData.checkHeight(test)) {
                continue;
            }
            height = Double.parseDouble(test);
        }
        return height;
    }

    public LocalDate createBirthDay() {
        String birthday = "";
        while (birthday.equals("")) {
            birthday = "";
            System.out.println("enter birthday in format MM-dd-yyyy");
            String test = properRead("birthday must not be null");

            if (!checkData.checkBirthday(test)) {
                continue;
            }
            birthday = test;
        }
        return LocalDateAdapter.getInstance().unmarshal(birthday);
    }


    public Color createColor() {
        Color color = null;

        while (color == null) {

            System.out.println("choose colour");
            System.out.println("choose one number");
            System.out.println(Arrays.toString(Color.colors()));
            String test = properRead("Color must not be null");
            CheckData checkData = new CheckData();
            if (!checkData.checkColourReadingConsole(test)) {
                continue;
            }
            color = Color.newValue(test);
        }
        return color;
    }


    public Country createNationality() {
        Country nationality = null;

        while (nationality == null) {

            System.out.println("choose nationality");
            System.out.println("choose one  number");

            System.out.println(Arrays.toString(Country.countries()));
            String test = properRead("country must not be null");
            if (!checkData.checkNationalityReadingFromConsole(test)) {
                continue;
            }
            nationality = Country.newValue(test);
        }
        return nationality;
    }


    public Coordinates createCoordinates() {
        String x = "";
        String y = "";

        while (x.equals("")) {

            System.out.println("enter coordinates ");

            System.out.println("enter x");

            String test = properRead("x must not be null");

            if (!checkData.checkCoordinateX(test)) {
                continue;
            }
            x = test;
        }
        while (y.equals("")) {
            System.out.println("enter y");

            String test = properRead("y must not be null");
            if (!checkData.checkCoordinateY(test)) {
                continue;
            }
            y = test;

        }
        return Coordinates
                .builder()
                .x(x)
                .y(y)
                .build();
    }


    public Location createLocation() {
        String x = "";
        String y = "";
        String z = "";
        String name = "";


        while (x.equals("")) {
            System.out.println("enter Location name and it's coordinates ");

            System.out.println("enter  x");

            String test = properRead("x must not be null");

            if (!checkData.checkLocationCoordinateX(test)) {
                continue;
            }

            x = test;
        }
        while (y.equals("")) {

            System.out.println("enter  y");

            String test = properRead("y must not be null");

            if (!checkData.checkLocationCoordinateX(test)) {
                continue;
            }
            y = test;
        }
        while (z.equals("")) {
            System.out.println("enter  z");

            String test = properRead("z must not be null");

            if (!checkData.checkLocationCoordinateZ(test)) {
                continue;
            }
            z = test;
        }
        while (name.equals("")) {
            System.out.println("enter location name");
            String test = properRead("location must not be null");

            if (!checkData.checkName(test)) {
                continue;
            }
            name = test;
        }
        return Location
                .builder()
                .z(Float.parseFloat(z))
                .y(Float.valueOf(y))
                .x(Double.parseDouble(x))
                .name(name)
                .build();
    }
}
