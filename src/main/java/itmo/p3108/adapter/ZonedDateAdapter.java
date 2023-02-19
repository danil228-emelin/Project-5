package itmo.p3108.adapter;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adapt ZonedDate Type for serialization
 */
@Slf4j
public class ZonedDateAdapter extends XmlAdapter<String, ZonedDateTime> {

    private static ZonedDateAdapter zonedDataAdapter;

    public static ZonedDateAdapter getInstance() {
        if (zonedDataAdapter == null) {
            zonedDataAdapter = new ZonedDateAdapter();
            log.info("Local Adapter initialized");

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
     * create LocalDate object from string
     */
    @Override
    public ZonedDateTime unmarshal(String v) {
        DateTimeFormatter formatter = dateFormat();
        log.info("Local Adapter unmarshal ZoneDateTime time from xml format");
        return ZonedDateTime.parse(v, formatter);
    }

    /**
     * unnecessary method
     */
    @Override
    public String marshal(ZonedDateTime v) {

        log.info("Local Adapter marshal ZoneDateTime time from xml format");

        return v.format(dateFormat());
    }
}
