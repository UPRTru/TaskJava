package GroupId.file;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class JsonAddress {
    private String city;
    private String street;
    private int house;
    private int floor;
    private final Charset charset = StandardCharsets.ISO_8859_1;

    public JsonAddress(String city, String street, int house, int floor) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
    }

    public JsonAddress() {
    }

    public String toStringLine(String format) {
        return switch (format) {
            case ("csv") -> "\"" + city +
                    "\";\"" + street +
                    "\";" + house +
                    ";" + floor;
            case ("xml") -> "<item city=\"" + city +
                    "\" street=\"" + street +
                    "\" house=\"" + house +
                    "\" floor=\"" + floor + "\" />";
            default -> "";
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JsonAddress that)) return false;
        return getHouse() == that.getHouse() && getFloor() == that.getFloor() && Objects.equals(getCity(), that.getCity()) && Objects.equals(getStreet(), that.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getHouse(), getFloor());
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getFloor() {
        return floor;
    }
}
