package itmo.p3108.adapter;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * class LocalDateAdapter adapts LocalDate  for serialization and deserialization in xml format
 */
@Slf4j
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static LocalDateAdapter localDateAdapter;


    public static LocalDateAdapter getInstance() {
        if (localDateAdapter == null) {
            localDateAdapter = new LocalDateAdapter();
        }
        return localDateAdapter;
    }

    /**
     * @return Create certain Date time format for Local Date
     * <p>
     * It needs for serialization
     */
    public DateTimeFormatter dateFormat() {
        return DateTimeFormatter.ofPattern("MM-dd-yyyy");
    }


    /**
     * create LocalDate object from string
     * <p>
     * It needs for deserialization
     * use dateFormat for unmarshalling.
     */
    @Override
    public LocalDate unmarshal(String v) {
        DateTimeFormatter formatter = dateFormat();
        return LocalDate.parse(v, formatter);
    }

    /**
     * marshal local date to formatted string
     * <p>
     * use dateFormat for converting data in certain format
     */
    @Override
    public String marshal(LocalDate v) {
        return v.format(dateFormat());
    }
}
