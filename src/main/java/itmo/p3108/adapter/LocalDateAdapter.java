package itmo.p3108.adapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    public DateTimeFormatter dateFormat() {
        return DateTimeFormatter.ofPattern("MM-dd-yyyy");
    }

    @Override
    public LocalDate unmarshal(String v) {
        DateTimeFormatter formatter = dateFormat();
        return LocalDate.parse(v, formatter);
    }

    @Override
    public String marshal(LocalDate v) {
        return v.format(dateFormat());
    }
}
