package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import itmo.p3108.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Analyzer {
    private Analyzer() {
    }

    public static void analyze(String... commands) {
        Invoker invoker = Invoker.getInstance();

        String command = commands[0];

        if (command.equals("add")) {
            String[] arguments = commands[1].trim().split(",");
            if (arguments.length != 12) {
                System.err.println("Error add:next line must be parameters");
                System.err.println("Some attributes  missed");
                System.err.println("parameters format:id,name,coordinate.x,coordinate.y,height,birthday, eyeColor number ,nationality number,location.x,location.y,location.z,location.name");
                return;
            }
            if (
                    CheckData.checkId(arguments[0]) &&
                            CheckData.checkName(arguments[1]) &&
                            CheckData.checkCoordinateX(arguments[2]) &&
                            CheckData.checkCoordinateY(arguments[3]) &&
                            CheckData.checkHeight(arguments[4]) &&
                            CheckData.checkBirthday(arguments[5]) &&
                            CheckData.checkColourReadingConsole(arguments[6]) &&
                            CheckData.checkNationalityReadingFromConsole(arguments[7]) &&
                            CheckData.checkLocationCoordinateX(arguments[8]) &&
                            CheckData.checkLocationCoordinateY(arguments[9]) &&
                            CheckData.checkLocationCoordinateZ(arguments[10]) &&
                            CheckData.checkName(arguments[11])
            ) {
                Person person = Person.builder()
                        .id(Long.parseLong(arguments[0]))
                        .name(arguments[1])
                        .coordinates(Coordinates.builder().x(Integer.parseInt(arguments[2])).y(Float.valueOf(arguments[3])).build())
                        .height(Double.parseDouble(arguments[4]))
                        .birthday(LocalDate.parse(arguments[5], DateTimeFormatter.ofPattern("MM-dd-yyyy")))
                        .eyeColor(Color.newValue(arguments[6]))
                        .nationality(Country.newValue(arguments[7]))
                        .location(Location.builder()
                                .x(Double.parseDouble(arguments[8]))
                                .y(Float.valueOf(arguments[9]))
                                .z(Float.parseFloat(arguments[10]))
                                .name(arguments[11]).build())
                        .build();
                Command.controller.add(person);
            }

        } else {
            invoker.invoke(command);
        }

    }


}
