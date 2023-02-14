package itmo.p3108.adapter;

import itmo.p3108.parser.Parser;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adapt LocalDate Type for serialization
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static LocalDateAdapter localDateAdapter;

    public static LocalDateAdapter getInstance() {
        if (localDateAdapter == null) {
            localDateAdapter = new LocalDateAdapter();
        }
        return localDateAdapter;
    }

    public DateTimeFormatter dateFormat() {
        return DateTimeFormatter.ofPattern("MM-dd-yyyy");
    }


    @Override
    /**
     *
     */
    public LocalDate unmarshal(String v) {
        DateTimeFormatter formatter = dateFormat();
        return LocalDate.parse(v, formatter);
    }

    @Override
    /**
     * marshal Local Date
     */
    public String marshal(LocalDate v) {
        return v.format(dateFormat());
    }
}
