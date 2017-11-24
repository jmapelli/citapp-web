package pe.lizard.citapp.util;

public class IntegerUtil {

    public static Integer toInteger(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }

}
