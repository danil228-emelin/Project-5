package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import itmo.p3108.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Class AnalyzerExecuteScript analyze lines from execute script
 * it needs to detect add command
 */
public class AnalyzerExecuteScript {
    private static final String ADD_EXECUTE_SCRIPT_EXCEPTION1 = "Error add:next line empty ,but it  must have arguments";
    private static final String ADD_PROPER_ARGUMENTS_ORDER = "parameters format:id,name,coordinate.x,coordinate.y,height,birthday, eyeColor number ,nationality number,location.x,location.y,location.z,location.name";
    private static final String ADD_EXECUTE_SCRIPT_EXCEPTION2 = "Error add:some attributes are missed in argument line";

    private AnalyzerExecuteScript() {
    }

    public static void analyze(String... commands) {

        Invoker invoker = Invoker.getInstance();
        var i = 0;
        while (i < commands.length) {

            String command = commands[i].trim().toLowerCase();
            if (!command.equals("add")) {
                invoker.invoke(command);
                i++;
                continue;
            }

            if (i == commands.length - 1) {

                System.err.println(ADD_EXECUTE_SCRIPT_EXCEPTION1);
                System.err.println(ADD_PROPER_ARGUMENTS_ORDER);
                i++;
                continue;
            }
            String addArguments = commands[i + 1];
            if (addArguments.trim().split(",").length != 12) {
                System.err.printf("%s,line %d\n", ADD_EXECUTE_SCRIPT_EXCEPTION2, i);
                System.err.println(ADD_PROPER_ARGUMENTS_ORDER);
                i += 2;
                continue;
            }

            String[] arguments = addArguments.trim().split(",");
            CheckData checkData = new CheckData();
            if (checkData.wrapperCheckArguments(Arrays.stream(arguments).toList())) {
                Person person = Person.builder()
                        .id(Long.parseLong(arguments[0].trim()))
                        .name(arguments[1].trim())
                        .coordinates(Coordinates.builder().x(arguments[2]).y(arguments[3].trim()).build())
                        .height(Double.parseDouble(arguments[4].trim()))
                        .birthday(LocalDate.parse(arguments[5].trim(), DateTimeFormatter.ofPattern("MM-dd-yyyy")))
                        .eyeColor(Color.newValue(arguments[6].trim()))
                        .nationality(Country.newValue(arguments[7].trim()))
                        .location(Location.builder()
                                .x(Double.parseDouble(arguments[8].trim()))
                                .y(Float.valueOf(arguments[9].trim()))
                                .z(Float.parseFloat(arguments[10].trim()))
                                .name(arguments[11].trim()).build())
                        .build();
                Command.controller.getPersonList().add(person);
            }
            i += 2;
        }


    }
}
