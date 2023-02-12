package itmo.p3108.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateAdapter extends XmlAdapter<String, ZonedDateTime> {

    private static ZonedDateAdapter zonedDataAdapter;

    public static ZonedDateAdapter getInstance() {
        if (zonedDataAdapter== null) {
            zonedDataAdapter = new ZonedDateAdapter();
        }
        return zonedDataAdapter;
    }
    public DateTimeFormatter dateFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }

    @Override
    public ZonedDateTime unmarshal(String v) {
        DateTimeFormatter formatter = dateFormat();
        return ZonedDateTime.parse(v, formatter);
    }

    @Override
    public String marshal(ZonedDateTime v) {
        return v.format(dateFormat());
    }
}
