package itmo.p3108.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Adapt ZonedDate Type for serialization
 */

public class ZonedDateAdapter extends XmlAdapter<String, ZonedDateTime> {

    private static ZonedDateAdapter zonedDataAdapter;

    public static ZonedDateAdapter getInstance() {
        if (zonedDataAdapter== null) {
            zonedDataAdapter = new ZonedDateAdapter();
        }
        return zonedDataAdapter;
    }
    /**
     * @return create certain Date format
     */
    public DateTimeFormatter dateFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }
    /**
     *create LocalDate object from string
     *
     */
    @Override
    public ZonedDateTime unmarshal(String v) {
        DateTimeFormatter formatter = dateFormat();
        return ZonedDateTime.parse(v, formatter);
    }
    /**
     *
     * unnecessary method
     */
    @Override
    public String marshal(ZonedDateTime v) {
        return v.format(dateFormat());
    }
}
