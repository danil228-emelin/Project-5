package itmo.p3108.adapter;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adapt ZonedDate Type for serialization and deserialization
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
     * @return Create certain Date time format for Zoned Date
     * <p>
     * It needs for serialization
     */
    public DateTimeFormatter dateFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }

    /**
     * create Zone Data object from string
     * <p>
     * It needs for deserialization
     * use dateFormat for unmarshalling.
     */
    @Override
    public ZonedDateTime unmarshal(String v) {
        DateTimeFormatter formatter = dateFormat();
        log.info("Local Adapter unmarshal ZoneDateTime time from xml format");
        return ZonedDateTime.parse(v, formatter);
    }

    /**
     * marshal Zone date to formatted string
     * <p>
     * use dateFormat for converting data in certain format
     */
    @Override
    public String marshal(ZonedDateTime v) {

        log.info("Local Adapter marshal ZoneDateTime time from xml format");

        return v.format(dateFormat());
    }
}
