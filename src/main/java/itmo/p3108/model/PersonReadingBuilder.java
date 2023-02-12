package itmo.p3108.command.util;

import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.model.Color;
import itmo.p3108.model.Coordinates;
import itmo.p3108.model.Country;
import itmo.p3108.model.Location;
import itmo.p3108.util.UserReader;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public final class PersonReadingBuilder {
    private static PersonReadingBuilder createPerson;
    private static Long id = 1L;

    private PersonReadingBuilder() {
    }


    public static PersonReadingBuilder getInstance() {
        if (createPerson == null) {
            createPerson = new PersonReadingBuilder();
        }
        return createPerson;
    }

    private String properRead(String message) {
        return Objects.requireNonNull(UserReader.read(), message).trim();
    }

    public String createName() {
        String name = "";
        while (name.equals("")) {
            System.out.println("Введите имя пользователя");
            name = properRead("name must not be null");
            if (name.equals("")) {
                System.err.println("Ошибка:строка не может быть пустой");
                continue;
            }
            if (name.length() > 40) {
                System.err.println("Ошибка:строка слишком длинная");
                name="";
                continue;
            }
            if (!name.matches("\\w+-?\\w*")) {
                System.err.println("Ошибка:имя содержит неверные символы");
                name="";
            }
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

            System.out.println("Введите рост пользователя");
            test = properRead("height must not be null");

            if (!test.matches("-?\\d+\\.?\\d+") && !test.matches("-?\\d+")) {
                System.err.println("Ошибка:значение может быть либо дробным либо целым числом");
                continue;
            }

            if (Double.parseDouble(test) <= 0) {
                System.err.println("Ошибка:рост не может быть отрицательным либо равен нулю");
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
            System.out.println("Введите дату рождение в формате MM-dd-yyyy");
            String test = properRead("birthday must not be null");

            if (!test.matches("\\d{2}-\\d{2}-\\d{4}")) {
                System.err.println("Ошибка:строка не совпадает с форматом");
                continue;
            }
            String[] strings = test.split("-");

            if (Integer.parseInt(strings[0]) > 12) {
                System.err.println("Ошибка:месяц не может быть больше 12");
                continue;
            }

            if (Integer.parseInt(strings[1]) > 31) {
                System.err.println("Ошибка:дата не может быть больше 31");
                continue;
            }

            if (Integer.parseInt(strings[2]) > 2023 || Integer.parseInt(strings[2]) < 1930) {
                System.err.println("Ошибка:вы либо должны родиться в будущем либо должны быть мертвы");
                continue;
            }
            birthday = test;
        }
        return LocalDateAdapter.getInstance().unmarshal(birthday);
    }


    public Color createColor() {
        Color color = null;

        while (color == null) {

            System.out.println("Выберете цвета глаз пользователя");

            System.out.println(Arrays.toString(Color.colors()));
            String test = properRead("birthday must not be null");
            if (test.equals("")) {
                System.err.println("Ошибка:строка не может быть пустой");
                continue;
            }
            color = Color.newValue(test);
            if (color == null) {
                System.err.println("Ошибка:строка имела неверный формат");
            }
        }
        return color;
    }


    public Country createNationality() {
        Country nationality = null;

        while (nationality == null) {

            System.out.println("Выберете гражданство пользователя");

            System.out.println(Arrays.toString(Country.countries()));
            String test = properRead("country must not be null");
            if (test.equals("")) {
                System.err.println("Ошибка:строка не может быть пустой");
                continue;
            }
            nationality = Country.newValue(test);
            if (nationality == null) {
                System.err.println("Ошибка:строка имела неверный формат");
            }
        }
        return nationality;
    }


    public Coordinates createCoordinates() {
        String x = "";
        String y = "";

        while (x.equals("")) {

            System.out.println("Введените координаты пользователя ");

            System.out.println("Введите значение x");

            String test = properRead("x must not be null");

            if (!test.matches("-?\\d+")) {
                System.err.println("Ошибка:значение  x должно содержать только целое  значение");
                continue;

            }
            x = test;
        }
        while (y.equals("")) {
            System.out.println("Введите значение y");

            String test = properRead("y must not be null");
            if (!test.matches("-?\\d+\\.?\\d+") && !test.matches("-?\\d+")) {
                System.err.println("Ошибка:значение  y может быть только дробным или целым числом");
                continue;

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
            System.out.println("Введените координаты локации пользователя ");

            System.out.println("Введите значение x");

            String test = properRead("x must not be null");

            if (!test.matches("-?\\d+\\.?\\d+") && !test.matches("-?\\d+")) {
                System.err.println("Ошибка:x может быть только целым или дробным числом");
                continue;

            }
            x = test;
        }
        while (y.equals("")) {

            System.out.println("Введите значение y");

            String test = properRead("y must not be null");

            if (!test.matches("-?\\d+\\.?\\d+") && !test.matches("-?\\d+")) {
                System.err.println("Ошибка:y может быть только дробным или целым числом");
                continue;

            }
            y = test;
        }
        while (z.equals("")) {
            System.out.println("Введите значение z");

            String test = properRead("z must not be null");

            if (!test.matches("-?\\d+\\.?\\d+") && !test.matches("-?\\d+")) {
                System.err.println("Ошибка:z может быть только дробным или целым числом");
                continue;

            }
            z = test;
        }
        while (name.equals("")) {
            System.out.println("Введите название локации");
            String test = properRead("location must not be null");

            if (!test.matches("\\w+")) {
                System.err.println("Ошибка:имя содержит неверные символы");
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
