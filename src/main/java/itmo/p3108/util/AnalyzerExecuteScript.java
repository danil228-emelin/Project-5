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
            CheckData checkData = new CheckData();
            if (
                    checkData.checkId(arguments[0]) &&
                            checkData.checkName(arguments[1]) &&
                            checkData.checkCoordinateX(arguments[2]) &&
                            checkData.checkCoordinateY(arguments[3]) &&
                            checkData.checkHeight(arguments[4]) &&
                            checkData.checkBirthday(arguments[5]) &&
                            checkData.checkColourReadingConsole(arguments[6]) &&
                            checkData.checkNationalityReadingFromConsole(arguments[7]) &&
                            checkData.checkLocationCoordinateX(arguments[8]) &&
                            checkData.checkLocationCoordinateY(arguments[9]) &&
                            checkData.checkLocationCoordinateZ(arguments[10]) &&
                            checkData.checkName(arguments[11])
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