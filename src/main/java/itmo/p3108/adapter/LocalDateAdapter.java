package itmo.p3108.adapter;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adapt LocalDate Type for serialization
 */
@Slf4j
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static LocalDateAdapter localDateAdapter;


    public static LocalDateAdapter getInstance() {
        if (localDateAdapter == null) {
            localDateAdapter = new LocalDateAdapter();
            log.info("Local Adapter initialized");
        }
        return localDateAdapter;
    }

    /**
     * @return Create certain Date format
     */
    public DateTimeFormatter dateFormat() {
        return DateTimeFormatter.ofPattern("MM-dd-yyyy");
    }


    /**
     * create LocalDate object from string matched date format
     */
    @Override
    public LocalDate unmarshal(String v) {
        DateTimeFormatter formatter = dateFormat();
        log.info("Local Adapter  unmarshal  LocalDate time from xml format");
        return LocalDate.parse(v, formatter);
    }

    /**
     * marshal local date to formatted string
     */
    @Override
    public String marshal(LocalDate v) {
        log.info("Local Adapter marshal  LocalDate time in  xml format");
        return v.format(dateFormat());
    }
}
