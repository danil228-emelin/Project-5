package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import itmo.p3108.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnalyzerExecuteScript {
    private AnalyzerExecuteScript() {
    }

    public static void analyze(String... commands) {
        Invoker invoker = Invoker.getInstance();

        for (int i = 0; i < commands.length; i++) {

            String command = commands[i].trim();
            if (!command.startsWith("add")) {
                invoker.invoke(command);
                continue;
            }

            if (i == commands.length - 1) {

                System.err.println("Error add:next line empty ,but it  must have arguments");
                System.err.println("parameters format:id,name,coordinate.x,coordinate.y,height,birthday, eyeColor number ,nationality number,location.x,location.y,location.z,location.name");

                continue;
            }
            if (commands[i + 1].trim().split(",").length != 12) {
                System.err.println("Error add:some attributes are missed in argument line " + i);
                System.err.println("parameters format:id,name,coordinate.x,coordinate.y,height,birthday, eyeColor number ,nationality number,location.x,location.y,location.z,location.name");

                continue;
            }
            String[] arguments = commands[1].trim().split(",");

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

        }


    }
}
