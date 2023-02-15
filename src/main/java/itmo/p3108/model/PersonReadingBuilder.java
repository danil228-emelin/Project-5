package itmo.p3108.model;

import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.util.UserReader;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public final class PersonReadingBuilder {
    private static PersonReadingBuilder createPerson;
    private static Long id = 1L;

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

            if (test.length() > 40) {
                System.err.println("error:too long line");
                continue;
            }
            if (!test.matches("(\\w+-?\\w*)")) {
                System.err.println("error:line has wrong format");
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

            if (!test.matches("\\d+\\.?\\d*")) {
                System.err.println("error:value is whole or fractional number");
                continue;
            }
            if (test.length() > 15) {
                System.err.println("error:value is too large");
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

            if (!test.matches("\\d{2}-\\d{2}-\\d{4}")) {
                System.err.println("error:line has wrong format");
                continue;
            }
            String[] strings = test.split("-");

            if (Integer.parseInt(strings[0]) > 12 || Integer.parseInt(strings[1]) > 31
                    || Integer.parseInt(strings[2]) > 2024 || Integer.parseInt(strings[2]) < 1920) {
                System.err.println("error:value is incorrect");
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
            System.out.println("wright corresponding number");
            System.out.println(Arrays.toString(Color.colors()));
            String test = properRead("Color must not be null");
            if (!test.matches("[1-5]")) {
                System.err.println("error:line has wrong format");
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
            System.out.println("wright corresponding number");

            System.out.println(Arrays.toString(Country.countries()));
            String test = properRead("country must not be null");
            if (!test.matches("[1-4]")) {
                System.err.println("error:line has wrong format");
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

            if (!test.matches("-?\\d+")) {
                System.err.println("error:wrong format for x");
                continue;

            }
            if (test.length() > 15) {
                System.err.println("error:value is too large");
            }
            x = test;
        }
        while (y.equals("")) {
            System.out.println("enter y");

            String test = properRead("y must not be null");
            if (!test.matches("-?\\d+\\.?\\d*")) {
                System.err.println("error:value is whole or fractional number");
                continue;

            }
            if (test.length() > 15) {
                System.err.println("error:value is too large");
            }
            y = test;

        }
        return Coordinates
                .builder()
                .x(Integer.parseInt(x))
                .y(Float.valueOf(y))
                .build();
    }


    public Location createLocation() {
        String x = "";
        String y = "";
        String z = "";
        String name = "";


        while (x.equals("")) {
            System.out.println("enter coordinates ");

            System.out.println("enter  x");

            String test = properRead("x must not be null");

            if (!test.matches("-?\\d+\\.?\\d+") && !test.matches("-?\\d+")) {
                System.err.println("error:wrong format");
                continue;

            }
            if (test.length() > 15) {
                System.err.println("error:value is too large");
            }

            x = test;
        }
        while (y.equals("")) {

            System.out.println("enter  y");

            String test = properRead("y must not be null");

            if (!test.matches("-?\\d+\\.?\\d+") && !test.matches("-?\\d+")) {
                System.err.println("error:wrong format");
                continue;

            }
            if (test.length() > 15) {
                System.err.println("error:value is too large");
            }
            y = test;
        }
        while (z.equals("")) {
            System.out.println("enter  z");

            String test = properRead("z must not be null");

            if (!test.matches("-?\\d+\\.?\\d+") && !test.matches("-?\\d+")) {
                System.err.println("error:wrong format");
                continue;

            }
            if (test.length() > 15) {
                System.err.println("error:value is too large");
            }
            z = test;
        }
        while (name.equals("")) {
            System.out.println("enter location name");
            String test = properRead("location must not be null");

            if (!test.matches("\\w+")) {
                System.err.println("error:wrong format");
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
